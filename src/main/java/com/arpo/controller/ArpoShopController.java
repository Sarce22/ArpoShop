package com.arpo.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.arpo.models.CategoryProduct;
import com.arpo.models.Product;
import com.arpo.models.Rol;
import com.arpo.models.User;
import com.arpo.service.CategoryProductService;
import com.arpo.service.ProductService;
import com.arpo.singleton.Singleton;

import jakarta.servlet.http.HttpSession;

@Controller
public class ArpoShopController {

	@Autowired
    private Singleton userSingleton;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryProductService categoryService;
	
	
	@GetMapping({"/"})
	public String ShowLogin() {
		return "login";
	}
	
	@GetMapping({"/signIn"})
	public String signIn() {
		return "signIn";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/";
	}
	

    @PostMapping("/userProfile")
    public String login(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
        Optional<User> user = userSingleton.login(email, password);

        if (user.isPresent()) {
            Rol userRole = user.get().getIdRol();
            model.addAttribute("user", user.get());
            ArrayList<Product> products = (ArrayList<Product>) productService.getActiveProducts();
    	    ArrayList<CategoryProduct> categories = (ArrayList<CategoryProduct>) categoryService.listCategory();
    	    if (products.isEmpty()) {
    	        model.addAttribute("noProductsMessage", "No hay productos disponibles en este momento.");
    	    } else {
    	        model.addAttribute("products", products);
    	    }
    	    session.setAttribute("userId", user.get().getIdUser());
    	    model.addAttribute("categories", categories);
            if (userRole.getName_rol().equals("Administrador")) {
                return "exploreProducts"; 
            } else if (userRole.getName_rol().equals("Cliente")) {
            	return "client/clientTemplate"; 
            }
        }
        model.addAttribute("errorMessage", "Credenciales incorrectas. Intente nuevamente.");
        return "login"; // Página de error o login nuevamente
    }






	
}
