package com.arpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.arpo.models.Product;
import com.arpo.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

	
    private List<Product> cart = new ArrayList<>(); 

    @Autowired
    private ProductService productService;

    @GetMapping("/showCart")
    public String verCarrito(Model model) {
        double total = calcularPrecioTotal();
        model.addAttribute("products", cart);
        model.addAttribute("total", total);
        return "cart/view-cart"; 
    }

    @PostMapping("/agregar-producto/{productId}")
    public String agregarProductoAlCarrito(@PathVariable Long productId, Model model) {
        
        Product product = productService.getByIdProduct(productId);
        
        if (product != null) {
            cart.add(product); 
        }
        return "redirect:/cart/showCart";
    }


    private double calcularPrecioTotal() {
        double total = 0.0;
        for (Product product : cart) {
            total += product.getPrice(); 
        }
        return total;
    }
}

	/*
	@PostMapping("/saveProductCart")
	public String saveProduct(@ModelAttribute Product product, Model model) { 
	    if (product != null && product.getIdCategory() != null && product.getIdSupplier() != null) {
	        Long idCategory = product.getIdCategory().getIdCategoryProduct();
	        CategoryProduct category = categoryService.getById(idCategory);
	        if (category != null) {
	            product.setIdCategory(category);
	        }
	        
	        Long idSupplier = product.getIdSupplier().getIdSupplier();
	        Supplier supplier = supplierService.getById(idSupplier);
	        if (supplier != null) {
	            product.setIdSupplier(supplier);
	        }
	        
	        cartService.saveProduct(product);
	    }
	    return "redirect:/cart/listProductCart";
	}*/
	
	
