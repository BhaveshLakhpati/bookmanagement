package com.bhaveshlakhapati.bookmanagement.cartservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhaveshlakhapati.bookmanagement.cartservice.feignclient.BookServiceFeignClient;
import com.bhaveshlakhapati.bookmanagement.commons.dto.BookDTO;

@Service
public class CartService {
	@Autowired
	private BookServiceFeignClient bookServiceFeignClient;

	public Optional<String> addToCart(final String isbn) {
		Optional<String> errorMessage;
		Optional<BookDTO> bookByISBN = this.bookServiceFeignClient.getBookByISBN(isbn).getBody();

		if (bookByISBN.isPresent()) {
			if (bookByISBN.get().getQuantity() > 0) {
				// TODO: Add user-book mapping in cart

				errorMessage = Optional.empty();
			} else {
				errorMessage = Optional.of("Book out of stock");
			}
		} else {
			errorMessage = Optional.of("Book with ISBN not found.");
		}

		return errorMessage;
	}
}
