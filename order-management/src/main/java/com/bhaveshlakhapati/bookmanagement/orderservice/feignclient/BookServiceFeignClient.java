package com.bhaveshlakhapati.bookmanagement.orderservice.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bhaveshlakhapati.bookmanagement.commons.entity.Book;

@FeignClient(name = "BOOK-SERVICE")
public interface BookServiceFeignClient {
	@PostMapping(path = "/books-by-isbn")
	public ResponseEntity<List<Book>> getBooksByISBNList(@RequestBody final List<String> isbnList);
}
