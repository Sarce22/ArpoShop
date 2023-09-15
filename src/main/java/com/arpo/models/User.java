package com.arpo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name= "user")
public class User {
	
	@Id
	private Long idUser;
	
	@Column(name= "nameUser")
	private String name;
	
	@Column(name= "surnameUser")
	private String surname;
	
	private int age;
	
	private String email;
	
	private String password;
	
	private String addres;
	
	private String phoneNumber;
	
	@ManyToOne
	@JoinColumn (name = "idRol", referencedColumnName = "id_Rol")
	private Rol idRol;

	public User(Long idUser, String name, String surname, int age, String email,
			String password, String addres, String phoneNumber, Rol idRol) {
		super();
		this.idUser = idUser;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.email = email;
		this.password = password;
		this.addres = addres;
		this.phoneNumber = phoneNumber;
		this.idRol = idRol;
	}
	
	public User() {
		super();
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getsurname() {
		return surname;
	}

	public void setsurname(String surname) {
		this.surname = surname;
	}

	public int getage() {
		return age;
	}

	public void setage(int age) {
		this.age = age;
	}

	public String getemail() {
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}

	public String getpasswordSegura() {
		return password;
	}

	public void setpasswordSegura(String password) {
		this.password = password;
	}

	public String getaddres() {
		return addres;
	}

	public void setaddres(String addres) {
		this.addres = addres;
	}

	public String getphoneNumber() {
		return phoneNumber;
	}

	public void setphoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Rol getIdRol() {
		return idRol;
	}

	public void setIdRol(Rol idRol) {
		this.idRol = idRol;
	}
	
	

}
