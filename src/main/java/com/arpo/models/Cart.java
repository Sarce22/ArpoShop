package com.arpo.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idcart;
	
	@OneToMany(mappedBy = "cart")
    private List<Product> listProduct = new ArrayList<>();
	
	@Column(name="totalCarrito")
	private double total;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	
	public Cart() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdcart() {
		return idcart;
	}

	public void setIdcart(Long idcart) {
		this.idcart = idcart;
	}

	public List<Product> getListProduct() {
        return listProduct;
    }

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	 public void addProduct(Product product, int cant) {
	        if (product != null && cant > 0) {
	            
	            Product existingProduct = listProduct.stream()
	                    .filter(p -> p.getIdProduct().equals(product.getIdProduct()))
	                    .findFirst()
	                    .orElse(null);

	            if (existingProduct != null) {
	                int currentCant = existingProduct.getStock();
	                if (currentCant + cant <= existingProduct.getStock()) {
	                	
	                    existingProduct.setStock(currentCant + cant);
	                }
	            } else {
	                
	                int maxCant = product.getStock();
	                if (cant <= maxCant) {
	                    product.setStock(cant);
	                    listProduct.add(product);
	                }
	            }
	            
	            total = listProduct.stream()
	                    .mapToDouble(p -> p.getPrice() * p.getStock())
	                    .sum();
	        }
	    }
	

}