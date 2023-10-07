package com.bhaveshlakhapati.bookmanagement.cartservice.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bhaveshlakhapati.bookmanagement.cartservice.service.CartService;
import com.bhaveshlakhapati.bookmanagement.commons.controller.BaseController;
import com.bhaveshlakhapati.bookmanagement.commons.dto.CartDTO;
import com.bhaveshlakhapati.bookmanagement.commons.dto.CartRequestDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CartController extends BaseController {
	@Autowired
	private CartService cartService;

	@PostMapping(path = "/add-to-cart/{isbn}")
	public ResponseEntity<String> addToCart(@NotEmpty @RequestHeader final String userId,
			@PathVariable final String isbn) {
		log.info("ADD_TO_CART: {}, {}", userId, isbn);

		Optional<String> errorMessage = this.cartService.addToCart(userId, isbn);

		ResponseEntity<String> response;
		if (errorMessage.isPresent()) {
			response = failure(errorMessage.get());
		} else {
			response = success(null);
		}

		return response;
	}

	@DeleteMapping(path = "/remove-from-cart")
	public ResponseEntity<String> removeFromCart(@RequestBody final CartRequestDTO cartDTO) {
		ResponseEntity<String> response;
		Optional<String> errorMessage = this.cartService.removeFromCart(cartDTO);

		if (errorMessage.isPresent()) {
			response = failure(errorMessage.get());
		} else {
			response = success(null);
		}

		return response;
	}

	@GetMapping(path = "/get-cart-items/{userId}")
	public ResponseEntity<List<CartDTO>> getCartItems(@PathVariable final String userId) {
		List<CartDTO> cartItems = this.cartService.getCartItems(userId);

		return success(cartItems);
	}
}
