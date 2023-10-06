package com.bhaveshlakhapati.bookmanagement.orderservice.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bhaveshlakhapati.bookmanagement.commons.dto.CartDTO;

@FeignClient(name = "CART-SERVICE/cart-service")
public interface CartServiceFeignClient {
	@GetMapping(path = "/get-cart-items/{userId}")
	public ResponseEntity<List<CartDTO>> getCartItems(@PathVariable final String userId);
}
