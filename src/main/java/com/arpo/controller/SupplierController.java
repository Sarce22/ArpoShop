package com.arpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arpo.models.Supplier;
import com.arpo.service.SupplierService;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
	
	 @Autowired
	 private SupplierService supplierService;
	 
	 @GetMapping("/registroSupplier")
	 public String showformSupplier(Model model) {
		 model.addAttribute("supplier", new Supplier());
		 return "supplier/add-supplier";
	 }
	 
	 @PostMapping("/saveSupplier")
	 public String saveSupplier(@ModelAttribute Supplier supplier, Model model) {
	     boolean supplierId = supplierService.alReadyExist(supplier.getIdSupplier());
	     if (supplierId) {
	         model.addAttribute("errorIdDuplicado", true);
	     } else {
	         supplierService.save(supplier);
	     }
	     return "redirect:/supplier/registroSupplier";
	 }
}
