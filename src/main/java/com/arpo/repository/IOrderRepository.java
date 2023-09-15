package com.arpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arpo.models.Order;


public interface IOrderRepository extends JpaRepository<Order, Integer> {

}
