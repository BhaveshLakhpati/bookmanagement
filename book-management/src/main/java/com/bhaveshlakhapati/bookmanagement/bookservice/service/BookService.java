package com.bhaveshlakhapati.bookmanagement.bookservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bhaveshlakhapati.bookmanagement.bookservice.entity.Book;
import com.bhaveshlakhapati.bookmanagement.bookservice.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;

	public List<Book> getAvailableBooks() {
		List<Book> availableBooks = this.bookRepository.findAllWhereQuantityGreaterThan(0);

		return availableBooks;
	}

	public Optional<Book> getBookByISBN(final String isbn) {
		Optional<Book> book = this.bookRepository.findByISBN(isbn);

		return book;
	}

	@Transactional
	public Optional<Book> increaseQuantity(final long bookId, final int quantity) {
		Optional<Book> book = this.bookRepository.findById(bookId);

		if (book.isPresent()) {
			Book existingBook = book.get();
			existingBook.setQuantity(existingBook.getQuantity() + quantity);
			existingBook = this.bookRepository.save(existingBook);

			book = Optional.of(existingBook);
		}

		return book;
	}
}
