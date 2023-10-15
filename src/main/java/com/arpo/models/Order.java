package com.arpo.models;

import java.io.Serializable;
import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity (name = "orderUser")
public class Order implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOrder;
	
	@Column (name = "statusOrder")
	private boolean status;
	
	private Date dateOrder;
	
	@Column (name = "stockOrder")
	private int stock;
	
	@ManyToOne
	@JoinColumn (name = "UserOrder" )
	private User user;

	public Order(int idOrder, boolean status, Date dateOrder, int stock, User user) {
		super();
		this.idOrder = idOrder;
		this.status = status;
		this.dateOrder = dateOrder;
		this.stock = stock;
		this.user = user;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public boolean isstatus() {
		return status;
	}

	public void setstatus(boolean status) {
		this.status = status;
	}

	public Date getdateOrder() {
		return dateOrder;
	}

	public void setdateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}

	public int getstock() {
		return stock;
	}

	public void setstock(int stock) {
		this.stock = stock;
	}

	public User getUsuario() {
		return user;
	}

	public void setUsuario(User user) {
		this.user = user;
	}
	
	//Hola 
}
