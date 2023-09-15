package com.arpo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpo.models.Product;
import com.arpo.repository.IProductRepository;
@Service
public class ProductService {

	@Autowired
	private IProductRepository productRepository;
	
	public List<Product> listarProductos (){
		return productRepository.findAll();
	}
	
	 public Product obtenerPorId(Long id) {
	    return productRepository.findById(id).orElse(null);
	 }

	 public Product guardarProducto(Product producto) {
        return productRepository.save(producto);
	 }

	 public void eliminarProducto(Long id) {
	    productRepository.deleteById(id);
	 }
}
