package com.arpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arpo.models.CategoryProduct;
import com.arpo.models.Product;
import java.util.List;



public interface IProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByIdCategory(CategoryProduct idCategory);

}
