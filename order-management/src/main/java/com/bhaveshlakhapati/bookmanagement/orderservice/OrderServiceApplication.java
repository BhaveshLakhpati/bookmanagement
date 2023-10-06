package com.bhaveshlakhapati.bookmanagement.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EntityScan(basePackages = { "com.bhaveshlakhapati.bookmanagement.commons.entity",
		"com.bhaveshlakhapati.bookmanagement.orderservice.entity" })
@EnableFeignClients
public class OrderServiceApplication {
	public static void main(final String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
}
