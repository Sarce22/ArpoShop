package com.arpo.models;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProduct;
	
	private String nameProduct;
	@Column(name = "stockProducto")
	private int stock;
	private String urlImagen;
	
	@ManyToOne
	@JoinColumn(name = "idcartProduct")
	private Cart idcart;
	
	@ManyToOne
	@JoinColumn(name = "idCategoryProduct")
	private CategoryProduct idCategory;
	
	@OneToOne
	@JoinColumn(name="idSupplierProduct")
	private Supplier idSupplier;
	
	@OneToOne
	@JoinColumn(name = "idInventoryProduct")
	private Inventory idInventary;

	
	
	public Product(int idProduct, String nameProduct, int stock, String urlImagen, Cart idcart,
			CategoryProduct idCategory, Supplier idSupplier, Inventory idInventary) {
		super();
		this.idProduct = idProduct;
		this.nameProduct = nameProduct;
		this.stock = stock;
		this.urlImagen = urlImagen;
		this.idcart = idcart;
		this.idCategory = idCategory;
		this.idSupplier = idSupplier;
		this.idInventary = idInventary;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public int getstock() {
		return stock;
	}

	public void setstock(int stock) {
		this.stock = stock;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public Cart getidcart() {
		return idcart;
	}

	public void setidcart(Cart idcart) {
		this.idcart = idcart;
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

	public Inventory getIdInventary() {
		return idInventary;
	}

	public void setIdInventary(Inventory idInventary) {
		this.idInventary = idInventary;
	}
	
}