package com.arpo.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name ="categoriaProducto")
public class CategoryProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCategoryProduct;
	
	private String nameCategory;
	
	@OneToMany(mappedBy = "idCategory")
	private List<Product> listCart;
	
	
	public CategoryProduct(Long idCategoryProduct, String nameCategory, List<Product> listCart) {
		super();
		this.idCategoryProduct = idCategoryProduct;
		this.nameCategory = nameCategory;
		this.listCart = listCart;
	}

	public Long getIdCategoryProduct() {
		return idCategoryProduct;
	}

	public void setIdCategoryProduct(Long idCategoryProduct) {
		this.idCategoryProduct = idCategoryProduct;
	}

	public String getNameCategory() {
		return nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}

	public List<Product> getListCart() {
		return listCart;
	}

	public void setListCart(List<Product> listCart) {
		this.listCart = listCart;
	}
		

}