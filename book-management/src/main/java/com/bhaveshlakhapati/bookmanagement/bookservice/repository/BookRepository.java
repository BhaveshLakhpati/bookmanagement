package com.bhaveshlakhapati.bookmanagement.bookservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhaveshlakhapati.bookmanagement.commons.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	Optional<Book> findByIsbn(final String isbn);

	List<Book> findAllByQuantityGreaterThan(final int quantity);

	List<Book> findAllByIsbnIn(final List<String> isbnList);
}