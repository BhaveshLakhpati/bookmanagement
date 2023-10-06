package com.bhaveshlakhapati.bookmanagement.orderservice.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bhaveshlakhapati.bookmanagement.commons.controller.BaseController;
import com.bhaveshlakhapati.bookmanagement.commons.dto.CartDTO;
import com.bhaveshlakhapati.bookmanagement.orderservice.feignclient.CartServiceFeignClient;
import com.bhaveshlakhapati.bookmanagement.orderservice.service.OrderService;

@RestController
public class OrderController extends BaseController {
	@Autowired
	private CartServiceFeignClient cartServiceFeignClient;

	@Autowired
	private OrderService orderService;

	@PostMapping(path = "/add-order")
	public <T extends Map<String, ? super Integer>> ResponseEntity<? super T> addOrder(
			@RequestBody final String userId) {
		Map<String, ? super Integer> insufficientStockDetails;
		ResponseEntity<List<CartDTO>> cartItemsResponse = this.cartServiceFeignClient.getCartItems(userId);
		ResponseEntity<? super T> response;

		if (cartItemsResponse.hasBody()) {
			List<CartDTO> cartItems = cartItemsResponse.getBody();
			insufficientStockDetails = this.orderService.addOrder(userId, cartItems);
			if (insufficientStockDetails.isEmpty()) {
				response = success(insufficientStockDetails);
			} else {
				response = failure(insufficientStockDetails);
			}
		} else {
			insufficientStockDetails = Collections.singletonMap("error", "Something went wrong.");
			response = failure(insufficientStockDetails);
		}

		return response;
	}
}
