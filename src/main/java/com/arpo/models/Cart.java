package com.arpo.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idcart;
	
	@OneToMany(mappedBy = "cart")
    private List<Product> listProduct;
	
	@Column(name="totalCarrito")
	private double total;
	
	@ManyToOne
	@JoinColumn (name = "UsuarioCarrito")
	private User user;

	public Cart(Long idcart, List<Product> listProduct, double total, User user) {
		super();
		this.idcart = idcart;
		this.listProduct = listProduct;
		this.total = total;
		this.user = user;
	}
	
	public Cart() {
		// TODO Auto-generated constructor stub
	}

	public Long getidcart() {
		return idcart;
	}

	public void setidcart(Long idcart) {
		this.idcart = idcart;
	}

	public List<Product> getlistProduct() {
		return listProduct;
	}

	public void setlistProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public User getuser() {
		return user;
	}

	public void setuser(User user) {
		this.user = user;
	}

	

}