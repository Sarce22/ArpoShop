package com.arpo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arpo.models.CategoryProduct;
import com.arpo.models.Order;
import com.arpo.models.Product;
import com.arpo.models.User;


public interface IOrderRepository extends JpaRepository<Order, Integer> {


	List<Order> findByUser(User user);
	
	List<Order> findByStatus(String name);
	

}
