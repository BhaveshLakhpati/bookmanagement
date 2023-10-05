package com.bhaveshlakhapati.bookmanagement.bookservice.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bhaveshlakhapati.bookmanagement.bookservice.service.BookService;
import com.bhaveshlakhapati.bookmanagement.commons.dto.BookDTO;
import com.bhaveshlakhapati.bookmanagement.commons.dto.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class BookController extends BaseController {
	@Autowired
	private BookService bookService;

	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping(path = "/all-books")
	public ResponseEntity<?> getAvbailableBooks() {
		List<BookDTO> bookDTOList = this.bookService.getAvailableBooks().stream()
				.map(book -> objectMapper.convertValue(book, BookDTO.class)).toList();

		return success(bookDTOList);
	}

	@GetMapping(path = "/book/{isbn}")
	public ResponseEntity<ResponseDTO<Optional<BookDTO>>> getBookByISBN(@PathVariable @NotEmpty final String isbn) {
		Optional<BookDTO> bookByISBN = this.bookService.getBookByISBN(isbn)
				.map(book -> this.objectMapper.convertValue(book, BookDTO.class));

		return success(bookByISBN);
	}
}
