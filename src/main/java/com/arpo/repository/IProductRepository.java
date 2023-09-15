package com.arpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arpo.models.Product;


public interface IProductRepository extends JpaRepository<Product, Long> {

}
