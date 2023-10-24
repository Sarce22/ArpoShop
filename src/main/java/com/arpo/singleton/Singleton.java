package com.arpo.singleton;

import org.springframework.stereotype.Component;

import com.arpo.models.Rol;
import com.arpo.models.User;

import jakarta.annotation.PostConstruct;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class Singleton {
	
    private ArrayList<User> listUser;

    @PostConstruct
    private void init() {
        listUser = leerUser();
        User user1 = new User(100L, "Sebastian", "Arce", 30, "sebas@eam.com", "user1", "123 Main St", "555-1234", new Rol(1, "Cliente"));
        User admin = new User(1010L, "Mariana", "Portela", 25, "admin@eam.com", "admin", "456 Elm St", "555-5678", new Rol(2, "Administrador"));
        User user2 = new User(1094L, "Juanpa", "Valencia", 30, "juanVa@eam.com", "user2", "12322 Barcelona St", "555-98752", new Rol(1, "Cliente"));
        
        listUser.add(user1);
        listUser.add(admin);
        listUser.add(user2);
        escribirObjetoListUser();
    }

    public ArrayList<User> getListUser() {
        return listUser;  
    }

    public void escribirObjetoListUser() {
        try {
            FileOutputStream archivo = new FileOutputStream("user.data");
            ObjectOutputStream escritor = new ObjectOutputStream(archivo);
            escritor.writeObject(listUser);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private ArrayList<User> leerUser() {
        try {
            FileInputStream archivo = new FileInputStream("user.data");
            ObjectInputStream lector = new ObjectInputStream(archivo);
            return (ArrayList<User>) lector.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    
    
    public Optional<User> login(String email, String password) {
        for (User user : listUser) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return Optional.of(user); 
            }
        }
        return Optional.empty();
    }
    
    
    public Optional<User> getUser(Long id) {
        for (User user : listUser) {
            if (user.getIdUser().equals(id)) {
                return Optional.of(user); 
            }
        }
        return Optional.empty();
    }

	

}
