package com.springboot.shoppingapporderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.shoppingapporderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> 
{

}
