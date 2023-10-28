package com.arpo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpo.models.Cart;
import com.arpo.repository.ICartRepository;
import com.arpo.singleton.Singleton;




@Service
public class CartService {
	
	@Autowired
    private ProductService productService;

    @Autowired
    private Singleton singleton;
    
    @Autowired
    private ICartRepository cartRepository;
    
    public Cart save(Cart cart) {
    	return cartRepository.save(cart);
    }
    

    
	
	
}
