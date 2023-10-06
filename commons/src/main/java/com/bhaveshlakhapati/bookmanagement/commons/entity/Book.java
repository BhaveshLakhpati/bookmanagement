package com.bhaveshlakhapati.bookmanagement.commons.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@Entity
@Table(name = Book.TABLE_NAME, uniqueConstraints = { @UniqueConstraint(columnNames = { Book_.ISBN }) })
public class Book extends BaseEntity {
	public static final String TABLE_NAME = "hg_books";

	@NotEmpty(message = "Book TITLE cannot be empty.")
	private String title;

	@NotEmpty(message = "Book AUTHOR cannot be empty.")
	private String author;

	@NotEmpty(message = "Book ISBN cannot be empty.")
	private String isbn;

	@Min(value = 1, message = "Book PRICE should be greater than 0")
	private Double price;

	@Min(value = 0, message = "Book QUANTITY should not be less than 0")
	private Integer quantity;

	@NotEmpty(message = "Book image cannot be empty.")
	private String imageURL;
}
