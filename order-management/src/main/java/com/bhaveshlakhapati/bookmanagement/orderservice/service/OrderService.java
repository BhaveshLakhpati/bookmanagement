package com.bhaveshlakhapati.bookmanagement.orderservice.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhaveshlakhapati.bookmanagement.commons.dto.CartDTO;
import com.bhaveshlakhapati.bookmanagement.commons.entity.Book;
import com.bhaveshlakhapati.bookmanagement.orderservice.entity.Order;
import com.bhaveshlakhapati.bookmanagement.orderservice.repository.BookRepository;
import com.bhaveshlakhapati.bookmanagement.orderservice.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private BookRepository bookRepository;

	@Transactional
	public Map<String, Integer> addOrder(final String userId, final List<CartDTO> cartItems) {
		List<String> isbnList = cartItems.stream().map(CartDTO::getIsbn).collect(Collectors.toList());

		// Step 1: find all books by ISBN
		List<Book> booksByISBN = this.bookRepository.findAllByIsbnIn(isbnList);

		// Step 2: create map of ISBN, Book
		Map<String, Book> stockDetails = booksByISBN.stream()
				.collect(Collectors.toMap(Book::getIsbn, Function.identity()));

		for (CartDTO cartItem : cartItems) {
			stockDetails.computeIfPresent(cartItem.getIsbn(), (isbn, book) -> {
				int newQuantity = book.getQuantity() - 1;
				book.setQuantity(newQuantity);

				return book;
			});
		}

		Map<String, Integer> insufficientStockDetails;

		// Step 3: check if stock of any book is insufficient
		List<Book> insufficientStock = stockDetails.values().stream().filter(book -> book.getQuantity() < 0)
				.collect(Collectors.toList());
		if (insufficientStock.isEmpty()) {

			// Step 4: create order objects
			List<Order> orders = cartItems.stream().map(cartItem -> {
				Book book = stockDetails.get(cartItem.getIsbn());

				return Order.builder().book(book).userId(userId).build();
			}).collect(Collectors.toList());

			this.bookRepository.saveAll(stockDetails.values());
			this.orderRepository.saveAll(orders);

			insufficientStockDetails = Collections.emptyMap();
		} else {
			insufficientStockDetails = insufficientStock.stream()
					.collect(Collectors.toMap(Book::getIsbn, Book::getQuantity));
		}

		return insufficientStockDetails;
	}
}
