package com.arpo.models;

import java.io.Serializable;
import java.util.Date;
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
	
	@ManyToOne
	@JoinColumn (name = "UserOrder" )
	private User user;

	private double total;
	
	public Order(int idOrder, boolean status, Date dateOrder, int stock, User user) {
		super();
		this.idOrder = idOrder;
		this.status = status;
		this.dateOrder = dateOrder;
		this.user = user;
	}

	public Order() {
		super();// TODO Auto-generated constructor stub
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

	public User getUsuario() {
		return user;
	}

	public void setUsuario(User user) {
		this.user = user;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
}
