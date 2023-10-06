package com.bhaveshlakhapati.bookmanagement.bookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EntityScan(basePackages = { "com.bhaveshlakhapati.bookmanagement.commons.entity" })
public class BookServiceApplication {
	public static void main(final String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}
}
