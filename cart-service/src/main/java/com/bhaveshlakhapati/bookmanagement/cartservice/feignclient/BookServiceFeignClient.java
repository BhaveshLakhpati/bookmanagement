package com.bhaveshlakhapati.bookmanagement.cartservice.feignclient;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.bhaveshlakhapati.bookmanagement.commons.dto.BookDTO;

@FeignClient(name = "BOOK-SERVICE")
public interface BookServiceFeignClient {
	@GetMapping(path = "/book/{isbn}")
	public ResponseEntity<Optional<BookDTO>> getBookByISBN(final String isbn);
}
