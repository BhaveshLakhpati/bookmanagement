package com.bhaveshlakhapati.bookmanagement.bookservice.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.bhaveshlakhapati.bookmanagement.bookservice.dto.ResponseDTO;

public abstract class BaseController {
	public <T> ResponseEntity<ResponseDTO> success(final T body) {
		ResponseDTO successResponse = ResponseDTO.builder().success(true).body(body).message(null).build();

		return ResponseEntity.ok(successResponse);
	}

	public ResponseEntity<ResponseDTO> failure(final String message) {
		ResponseDTO errorResponse = ResponseDTO.builder().success(false).message(message).body(Optional.empty())
				.build();

		return ResponseEntity.badRequest().body(errorResponse);
	}
}
