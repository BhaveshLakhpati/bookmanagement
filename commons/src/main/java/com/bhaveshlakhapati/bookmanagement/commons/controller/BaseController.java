package com.bhaveshlakhapati.bookmanagement.commons.controller;

import org.springframework.http.ResponseEntity;

public abstract class BaseController {
	public <T> ResponseEntity<T> success(final T body) {
		return ResponseEntity.ok(body);
	}

	public <T> ResponseEntity<T> failure(final T body) {
		return ResponseEntity.badRequest().body(body);
	}
}
