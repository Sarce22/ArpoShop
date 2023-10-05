package com.arpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.arpo.models.Product;
import com.arpo.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("/listProductCart")
	public String listCartProduct(Model model) {
		model.addAttribute("ListProducts",cartService.listCartProduct());
		return "cart/cart";	
	}
	
	@PostMapping("/addToCart")
	public String addToCart(@RequestParam("idProduct") Long idProduct, @RequestParam("cantidad") int cantidad) {
	    Product product = cartService.getByIdProduct(idProduct);
	  
	    cartService.saveProduct(product);
	    return "redirect:/cart/listProductCart";
	}

	
}
