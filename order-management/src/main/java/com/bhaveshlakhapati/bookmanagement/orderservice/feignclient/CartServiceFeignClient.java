package com.bhaveshlakhapati.bookmanagement.orderservice.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.bhaveshlakhapati.bookmanagement.commons.dto.CartDTO;

@FeignClient(name = "CART-SERVICE")
public interface CartServiceFeignClient {
	@GetMapping(path = "/get-cart-items/{userId}")
	public ResponseEntity<List<CartDTO>> getCartItems(final String userId);
}
