package com.bhaveshlakhapati.bookmanagement.orderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhaveshlakhapati.bookmanagement.commons.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findAllByIsbnIn(final List<String> isbnList);
}
