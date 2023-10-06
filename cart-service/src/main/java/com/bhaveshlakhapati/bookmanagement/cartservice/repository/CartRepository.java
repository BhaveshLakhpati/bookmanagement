package com.bhaveshlakhapati.bookmanagement.cartservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhaveshlakhapati.bookmanagement.cartservice.entity.Cart;
import com.bhaveshlakhapati.bookmanagement.commons.entity.Book;

public interface CartRepository extends JpaRepository<Cart, Long> {
	public void deleteByBookAndUserId(final Book book, final String userId);

	List<Cart> findByUserId(final String userId);
}
