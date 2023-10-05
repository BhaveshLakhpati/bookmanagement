package com.bhaveshlakhapati.bookmanagement.cartservice.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = Cart.TABLE_NAME)
public class Cart extends BaseEntity {
	public static final String TABLE_NAME = "hg_cart";
}
