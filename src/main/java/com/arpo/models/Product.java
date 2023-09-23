package com.arpo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduct;
	
	private String nameProduct;
	@Column(name = "stockProducto")
	private int stock;
	
	private String urlImagen;
	
	private double price;
	
	private String description;
	@ManyToOne
    @JoinColumn(name = "idcart") // Nombre de la columna que relaciona Product con Cart
    private Cart cart;
	
	@ManyToOne
	@JoinColumn(name = "idCategory", referencedColumnName="idCategoryProduct")
	private CategoryProduct idCategory;
	
	@ManyToOne
	@JoinColumn(name="idSupplier", referencedColumnName="idSupplier")
	private Supplier idSupplier;
	


	public Product() {
		super();
	}

	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Cart getCart() {
		return cart;
	}


	public void setCart(Cart cart) {
		this.cart = cart;
	}


	public CategoryProduct getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(CategoryProduct idCategory) {
		this.idCategory = idCategory;
	}

	public Supplier getIdSupplier() {
		return idSupplier;
	}

	public void setIdSupplier(Supplier idSupplier) {
		this.idSupplier = idSupplier;
	}
<<<<<<< HEAD

	public Inventory getIdInventary() {
		return idInventary;
	}

	public void setIdInventary(Inventory idInventary) {
		this.idInventary = idInventary;
	}

=======
>>>>>>> 1f60c1835200beab9888a61226dc2f5274f07c2b
	
}