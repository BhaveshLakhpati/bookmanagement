package com.bhaveshlakhapati.bookmanagement.cartservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bhaveshlakhapati.bookmanagement.commons.entity.BaseEntity;
import com.bhaveshlakhapati.bookmanagement.commons.entity.Book;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@Entity
@Table(name = Cart.TABLE_NAME)
public class Cart extends BaseEntity {
	public static final String TABLE_NAME = "hg_cart";

	@OneToOne
	private Book book;

	@Column(nullable = false)
	private String userId;
}
