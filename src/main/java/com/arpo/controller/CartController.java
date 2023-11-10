package com.arpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.arpo.models.Cart;
import com.arpo.models.Order;
import com.arpo.models.Product;
import com.arpo.models.User;
import com.arpo.service.CartService;
import com.arpo.service.OrderService;
import com.arpo.service.ProductService;
import com.arpo.service.UserService;

import jakarta.servlet.http.HttpSession;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {

	 @Autowired
	 private CartService cartService; 
	 
	 @Autowired
	 private ProductService productService;

	 @Autowired
	 private UserService userService;
	 
	 @Autowired
	 private OrderService orderService;
	 
	// para almacenar los detalles de la orden
		List<Cart> detalles = new ArrayList<>();

		// datos de la orden
		Order order = new Order();
	 
	 
	 @PostMapping("/agregar-producto/{id}")
		public String addCart(@PathVariable Long id, @RequestParam Integer cantidad, Model model) {
			Cart cart = new Cart();
			Product producto = new Product();
			double sumaTotal = 0;

			Optional<Product> optionalProducto = productService.get(id);;
			producto = optionalProducto.get();

			cart.setCantidad(cantidad);
			cart.setPrecio(producto.getPrice());
			cart.setNombre(producto.getNameProduct());
			cart.setTotal(producto.getPrice() * cantidad);
			cart.setProduct(producto);
			
			Long idProducto = producto.getIdProduct();
			boolean ingresado = detalles.stream().anyMatch(p -> p.getProduct().getIdProduct()==idProducto);
			
			if (!ingresado) {
				cart.setCantidad(cantidad);
				detalles.add(cart);
				
			}
			
			sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
			order.setTotal(sumaTotal);
			model.addAttribute("cart", detalles);
			model.addAttribute("order", order);
			cartService.save(cart);
			return "cart/view-cart";
		}
	 
	 @GetMapping("/showCart")
	 public String showCart (Model model) {
		 model.addAttribute("cart", detalles);
		 model.addAttribute("order", order);
		 return "cart/view-cart";
	 }
	 
	 
	 @GetMapping("/order")
		public String order(Model model, HttpSession session) {
			
			User usuario =userService.findById(Long.parseLong(session.getAttribute("userId").toString())).get();
			
			model.addAttribute("cart", detalles);
			model.addAttribute("order", order);
			model.addAttribute("user", usuario);
			
			return "cart/order";
		} 
	 
	 
	 @GetMapping("/saveOrder")
		public String saveOrder(HttpSession session ) {
			Date fechaCreacion = new Date();
			order.setdateOrder(fechaCreacion);
			
			User usuario =userService.findById(Long.parseLong(session.getAttribute("userId").toString())).get();
			
			order.setUsuario(usuario);
			orderService.save(order);
			
			for (Cart dt:detalles) {
				dt.setOrder(order);
				cartService.save(dt);
			}
			
			order = new Order();
			detalles.clear();
			
			return "redirect:/order/listOrder";
		}
	 
}

	//Comente algo
	
