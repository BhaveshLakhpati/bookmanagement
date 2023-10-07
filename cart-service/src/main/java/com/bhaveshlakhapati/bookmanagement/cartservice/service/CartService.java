package com.bhaveshlakhapati.bookmanagement.cartservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bhaveshlakhapati.bookmanagement.cartservice.entity.Cart;
import com.bhaveshlakhapati.bookmanagement.cartservice.feignclient.BookServiceFeignClient;
import com.bhaveshlakhapati.bookmanagement.cartservice.repository.CartRepository;
import com.bhaveshlakhapati.bookmanagement.commons.dto.CartDTO;
import com.bhaveshlakhapati.bookmanagement.commons.dto.CartRequestDTO;
import com.bhaveshlakhapati.bookmanagement.commons.entity.Book;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CartService {
	@Autowired
	private BookServiceFeignClient bookServiceFeignClient;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Transactional
	public Optional<String> addToCart(final String userId, final String isbn) {
		Optional<String> errorMessage;

		try {
			ResponseEntity<Book> bookByISBN = this.bookServiceFeignClient.getBookByISBN(isbn);

			if (bookByISBN.hasBody()) {
				Book book = bookByISBN.getBody();

				if (book.getQuantity() > 0) {
					Cart cart = Cart.builder().userId(userId).book(book).build();
					this.cartRepository.save(cart);

					errorMessage = Optional.empty();
				} else {
					errorMessage = Optional.of("Book out of stock");
				}
			} else {
				errorMessage = Optional.of("Book with ISBN not found.");
			}
		} catch (final NullPointerException exception) {
			errorMessage = Optional.of("Something went wrong.");
		}

		return errorMessage;
	}

	@Transactional
	public Optional<String> removeFromCart(final CartRequestDTO cartRequestDTO) {
		Optional<String> errorMessage;

		try {
			ResponseEntity<Book> bookByISBN = this.bookServiceFeignClient.getBookByISBN(cartRequestDTO.getIsbn());
			if (bookByISBN.hasBody()) {
				Book book = bookByISBN.getBody();
				this.cartRepository.deleteByBookAndUserId(book, cartRequestDTO.getUserId());

				errorMessage = Optional.empty();
			} else {
				errorMessage = Optional.of("Something went wrong.");
			}
		} catch (final NullPointerException exception) {
			errorMessage = Optional.of("Something went wrong.");
		}

		return errorMessage;
	}

	public List<CartDTO> getCartItems(final String userId) {
		List<CartDTO> bookISBNList = this.cartRepository.findByUserId(userId).stream()
				.map(cartItem -> this.objectMapper.convertValue(cartItem, CartDTO.class)).collect(Collectors.toList());

		return bookISBNList;
	}
}
