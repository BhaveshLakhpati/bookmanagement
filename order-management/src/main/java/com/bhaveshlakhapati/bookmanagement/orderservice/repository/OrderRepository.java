package com.bhaveshlakhapati.bookmanagement.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhaveshlakhapati.bookmanagement.orderservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
