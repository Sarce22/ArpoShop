package com.arpo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpo.models.CategoryProduct;
import com.arpo.models.Product;
import com.arpo.repository.IProductRepository;
@Service
public class ProductService {

	@Autowired
	private IProductRepository productRepository;
	
	public List<Product> listProduct (){
		return productRepository.findAll();
	}
	
	 public Product getByIdProduct(Long id) {
	    return productRepository.findById(id).orElse(null);
	 }

	 public Product saveProduct(Product producto) {
        return productRepository.save(producto);
	 }

	 public void deleteProduct(Long id) {
	    productRepository.deleteById(id);
	 }
	
	 public List<Product> getByCategory(Long idCategory){
		 CategoryProduct category = new CategoryProduct();
		 category.setIdCategoryProduct(idCategory);
		 return productRepository.findByIdCategory(category);
		 
	 }

	 public Iterable<Product> getActiveProducts() {
	     return productRepository.loadActiveProducts();
	 }
	 
	 public Iterable<Product> filterProductsByCategory(String name){
		 return productRepository.filterProductsByCategory(name);
	 
}
