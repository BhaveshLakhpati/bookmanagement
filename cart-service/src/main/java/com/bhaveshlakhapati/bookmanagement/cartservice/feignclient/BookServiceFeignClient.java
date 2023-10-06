package com.bhaveshlakhapati.bookmanagement.cartservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bhaveshlakhapati.bookmanagement.commons.entity.Book;

@FeignClient(name = "BOOK-SERVICE/book-service")
public interface BookServiceFeignClient {
	@GetMapping(path = "/book/{isbn}")
	public ResponseEntity<Book> getBookByISBN(@PathVariable final String isbn);
}
