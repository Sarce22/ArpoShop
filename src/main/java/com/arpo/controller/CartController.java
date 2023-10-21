package com.arpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.arpo.models.Cart;
import com.arpo.models.Product;
import com.arpo.models.User;
import com.arpo.service.CartService;
import com.arpo.service.ProductService;
import com.arpo.singleton.Singleton;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {

	 @Autowired
	 private CartService cartService;  

	 @Autowired
	 private Singleton singleton;
	 
	 
	 @GetMapping("/showCart")
	 public String verCarrito(Model model, HttpSession session) {
	     Long userId = (Long) session.getAttribute("userId");
	     
	     if (userId != null) {
	         Cart cart = cartService.getCartByUserId(userId);

	         if (cart != null) {
	             if (cart.getListProduct() != null) {
	                 model.addAttribute("cart", cart);
	                 return "cart/view-cart";
	             } else {
	                 model.addAttribute("error", "Tu carrito está vacío.");
	             }
	         } else {
	        	 model.addAttribute("error", "Tu carrito está vacío.");
	         }
	     }
	     
	     return "redirect:/login";
	 }

	 
	 
	 @PostMapping("/agregar-producto/{productId}")
	    public String agregarProductoAlCarrito(@PathVariable Long productId, @RequestParam("cant") int cant, HttpSession session) {
	        Long userId = (Long) session.getAttribute("userId");
	        if (userId != null) {
	            cartService.addToCart(userId, productId, cant);
	        }
	        return "redirect:/cart/showCart";
	    }
}

	
	
