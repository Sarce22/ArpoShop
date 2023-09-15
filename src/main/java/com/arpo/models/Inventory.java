package com.arpo.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Inventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idInventory;
	
	@OneToMany (mappedBy = "idInventary")
	private List<Product> ListInventory;
	private int stock;
	
	
	
	public Inventory(int idInventory, List<Product> listInventory, int stock) {
		super();
		this.idInventory = idInventory;
		this.ListInventory = listInventory;
		this.stock = stock;
	}
	
	public int getIdInventory() {
		return idInventory;
	}
	public void setIdInventory(int idInventory) {
		this.idInventory = idInventory;
	}
	public List<Product> getListInventory() {
		return ListInventory;
	}
	public void setListInventory(List<Product> listInventory) {
		ListInventory = listInventory;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	

}