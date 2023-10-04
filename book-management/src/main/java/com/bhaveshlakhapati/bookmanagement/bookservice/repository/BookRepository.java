package com.bhaveshlakhapati.bookmanagement.bookservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhaveshlakhapati.bookmanagement.bookservice.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	Optional<Book> findByISBN(final String isbn);

	List<Book> findAllWhereQuantityGreaterThan(final int quantity);
}
