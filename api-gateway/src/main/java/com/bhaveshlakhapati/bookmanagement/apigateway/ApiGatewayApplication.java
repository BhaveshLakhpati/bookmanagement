package com.bhaveshlakhapati.bookmanagement.apigateway;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@SpringBootApplication
@Component
public class ApiGatewayApplication implements WebFilter {
	public static void main(final String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Override
	public Mono<Void> filter(final ServerWebExchange exchange, final WebFilterChain filterChain) {
		String userIdInHeader = exchange.getRequest().getHeaders().getFirst("userId");
		if (userIdInHeader == null) {
			userIdInHeader = UUID.randomUUID().toString();
			exchange.mutate().request(exchange.getRequest().mutate().header("userId", userIdInHeader).build());

			exchange.getResponse().getHeaders().add("userId", userIdInHeader);
		}

		return filterChain.filter(exchange);
	}
}
