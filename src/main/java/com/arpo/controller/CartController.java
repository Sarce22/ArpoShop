package com.arpo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.arpo.models.CategoryProduct;
import com.arpo.models.Product;
import com.arpo.models.Supplier;
import com.arpo.service.CartService;
import com.arpo.service.CategoryProductService;
import com.arpo.service.SupplierService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private CategoryProductService categoryService;
	
	@Autowired
	private SupplierService supplierService;
	
	
	@GetMapping("/registroProductCart")
	public String showformProducts(Model model) {
		List<CategoryProduct> categories = categoryService.listCategory();
		List<Supplier> suppliers = supplierService.listSuppliers();
		model.addAttribute("category",categories);
		model.addAttribute("supplier", suppliers);
		model.addAttribute("product", new Product());
		return "product/productos-disponibles";
	}
	
	@GetMapping("/listProductCart")
	public String listCartProduct(Model model) {
	    List<Product> cartProducts = cartService.listCartProduct();
	    
	    
	    double totalPrice = 0.0;
	    for (Product product : cartProducts) {
	        totalPrice += product.getPrice() * product.getStock();
	    }

	    model.addAttribute("ListProductsCart", cartProducts);
	    model.addAttribute("totalPrice", totalPrice); 
	    return "cart/view-cart";
	}

	
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
	}

}
