package com.arpo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arpo.models.CategoryProduct;
import com.arpo.models.Product;
import com.arpo.models.Supplier;
import com.arpo.service.CategoryProductService;
import com.arpo.service.ProductService;
import com.arpo.service.SupplierService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryProductService categoryService;
	
	@Autowired
	private SupplierService supplierService;
	
	
	@GetMapping("/registroProduct")
	public String showformProducts(Model model) {
		List<CategoryProduct> categories = categoryService.listCategory();
		List<Supplier> suppliers = supplierService.listSuppliers();
		model.addAttribute("category",categories);
		model.addAttribute("supplier", suppliers);
		model.addAttribute("product", new Product());
		return "product/add-product";
	}
	
	
	@GetMapping("/listProducts")
	public String listProducts(Model model) {
		model.addAttribute("ListProducts", productService.listProduct());
		return "product/listProduct";
	}
	
	
	@PostMapping("/saveProduct")
	 public String saveProduct(@ModelAttribute Product product, Model model) { 
		Long idCategory = product.getIdCategory().getIdCategoryProduct();
		CategoryProduct category = categoryService.getById(idCategory);
		product.setIdCategory(category);
		
		Long idSupplier = product.getIdSupplier().getIdSupplier();
		Supplier supplier = supplierService.getById(idSupplier);
		product.setIdSupplier(supplier);
		
	    productService.saveProduct(product);
	     return "redirect:/product/registroProduct";
	 }
	
	
	@GetMapping("/admin/editProduct/{idProduct}")
	public String showUpdateProduct (@PathVariable("idProduct") Long idProduct, Model model) {
		
	    Product product = productService.getByIdProduct(idProduct);
	    
	    List<CategoryProduct> categories = categoryService.listCategory();
	    List<Supplier> suppliers = supplierService.listSuppliers();
	    
	    model.addAttribute("product", product);
	    model.addAttribute("categories", categories);
	    model.addAttribute("suppliers", suppliers);
	    
	    return "product/update-product";
	}
	
	
	@PostMapping("/admin/updateProduct/{idProduct}")
	public String updateProduct(@PathVariable("idProduct") Long idProduct,Product product,Model model) {
		Product alreadyproducts = productService.getByIdProduct(idProduct);
		if (alreadyproducts != null) {
			alreadyproducts.setNameProduct(product.getNameProduct());
			alreadyproducts.setStock(product.getStock());
			alreadyproducts.setIdCategory(product.getIdCategory());
			alreadyproducts.setIdSupplier(product.getIdSupplier());
			alreadyproducts.setUrlImagen(product.getUrlImagen());
			alreadyproducts.setDescription(product.getDescription());
			alreadyproducts.setPrice(product.getPrice());
			productService.saveProduct(alreadyproducts);
			model.addAttribute("successMessage", "El producto ha sido modificado.");
		}
		return "redirect:/product/listProducts";
	}
	
	@GetMapping("/admin/deleteProduct/{idProduct}")
	public String deleteProduct(@PathVariable("idProduct") Long idProduct, Model model) {
		Product products = productService.getByIdProduct(idProduct);
		productService.deleteProduct(products.getIdProduct());
		model.addAttribute("product", productService.listProduct());
		return "redirect:/product/listProducts";
	}
	
	
}
