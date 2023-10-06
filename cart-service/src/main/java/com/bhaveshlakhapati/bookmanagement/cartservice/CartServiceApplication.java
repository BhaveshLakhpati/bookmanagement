package com.bhaveshlakhapati.bookmanagement.cartservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EntityScan(basePackages = { "com.bhaveshlakhapati.bookmanagement.commons.entity",
		"com.bhaveshlakhapati.bookmanagement.cartservice.entity" })
public class CartServiceApplication {
	public static void main(final String[] args) {
		SpringApplication.run(CartServiceApplication.class, args);
	}
}
