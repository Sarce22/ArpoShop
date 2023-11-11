package com.arpo.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpo.models.Cart;
import com.arpo.models.Product;
import com.arpo.repository.ICartRepository;




@Service
public class CartService {
    
    @Autowired
    private ICartRepository cartRepository;
    
    public Cart save(Cart cart) {
    	return cartRepository.save(cart);
    }
    
}

