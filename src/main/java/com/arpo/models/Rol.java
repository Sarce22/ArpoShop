package com.arpo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity 
public class Rol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_Rol;
	
	private String name_rol;
	
	public Rol(int id_Rol, String name_rol) {
		super();
		this.id_Rol = id_Rol;
		this.name_rol = name_rol;
	}

	public Rol() {
		super();
	}
	public int getId_Rol() {
		return id_Rol;
	}

	public void setId_Rol(int id_Rol) {
		this.id_Rol = id_Rol;
	}


    public String getName_rol() {
        return name_rol;
    }

    public void setName_rol(String name_rol) {
        this.name_rol = name_rol;
    }
	
}
