package com.bhaveshlakhapati.bookmanagement.commons.dto;

import lombok.Data;

@Data
public class CartRequestDTO {
	private String isbn;

	private String userId;
}
