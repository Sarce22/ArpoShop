package com.arpo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Supplier {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSupplier;
	
	private String nameSupplier;
	
	@Column(name = "numberSupplier")
	private String phoneNumber;
	
	@Column(name = "addressSupplier")
	private String address;

	public Supplier(int idSupplier, String nameSupplier, String phoneNumber, String address) {
		super();
		this.idSupplier = idSupplier;
		this.nameSupplier = nameSupplier;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public int getIdSupplier() {
		return idSupplier;
	}

	public void setIdSupplier(int idSupplier) {
		this.idSupplier = idSupplier;
	}

	public String getnameSupplier() {
		return nameSupplier;
	}

	public void setnameSupplier(String nameSupplier) {
		this.nameSupplier = nameSupplier;
	}

	public String getphoneNumber() {
		return phoneNumber;
	}

	public void setphoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getaddress() {
		return address;
	}

	public void setaddress(String address) {
		this.address = address;
	}
		

}