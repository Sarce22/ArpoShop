package com.arpo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.arpo.models.CategoryProduct;
import com.arpo.models.Product;


public interface IProductRepository extends JpaRepository<Product, Long> {
	
	
	List<Product> findByIdCategory(CategoryProduct category);
	
}
