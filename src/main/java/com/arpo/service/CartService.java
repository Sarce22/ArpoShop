package com.arpo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpo.models.Product;
import com.arpo.repository.IProductRepository;



@Service
public class CartService {
	
	@Autowired
	private IProductRepository productRepository;
	
	public List<Product> listCartProduct(){
		return productRepository.findAll();
	}
	
	public Product saveProduct(Product producto) {
        return productRepository.save(producto);
	 }
	
	public void delete(Long id) {
	     productRepository.deleteById(id);
	 }
	

}
