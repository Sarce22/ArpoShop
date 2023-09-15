package com.arpo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpo.models.User;
import com.arpo.repository.IUserRepository;

@Service
public class UserService {
	
	@Autowired
	private IUserRepository userRepository;
	
	public List<User> listarUsuarios(){
		return userRepository.findAll();
	}
	
	 public User obtenerPorId(Long id) {
	        return userRepository.findById(id).orElse(null);
	    }

	    public User guardar(User user) {
	        return userRepository.save(user);
	    }

	    public void eliminar(Long id) {
	        userRepository.deleteById(id);
	    }
}
