package com.bhaveshlakhapati.bookmanagement.commons.dto;

import lombok.Data;

@Data
public class CartDTO {
	private BookDTO book;
	private String userId;
}
