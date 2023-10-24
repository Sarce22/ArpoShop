package com.arpo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpo.models.Cart;
import com.arpo.models.Product;
import com.arpo.models.User;
import com.arpo.singleton.Singleton;




@Service
public class CartService {
	
	@Autowired
    private ProductService productService;

    @Autowired
    private Singleton singleton;

    public Cart getCartByUserId(Long userId) {
        Optional<User> user = singleton.getUser(userId);

        if (user != null) {
            return user.get().getCart();
        }

        return null;
    }

    public void addToCart(Long userId, Long productId, int cant) {
    	
        Optional<User> user = singleton.getUser(userId);
        Product product = productService.getByIdProduct(productId);

        if (user != null && product != null) {
            Cart cart = user.get().getCart();
            if (cart == null) {
                cart = new Cart();
                user.get().setCart(cart);;
            }
            cart.addProduct(product, cant);
        }
    }

    public void saveCart(Long userId, Cart cart) {
        Optional<User> user = singleton.getUser(userId);

        if (user != null) {
            user.get().setCart(cart);
        }
    }
	
	
}
