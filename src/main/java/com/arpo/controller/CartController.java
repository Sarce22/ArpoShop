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
		

	 List<Cart> detalles = new ArrayList<Cart>();
	 Order order = new Order();
		
		
		@PostMapping("/agregar-producto/{id}")
		public String addCart(@PathVariable Long id, @RequestParam Integer cantidad, Model model) {
			Cart cart = new Cart();
			Product product = new Product();
			double sumaTotal = 0;

			Optional<Product> optionalProducto = productService.get(id);
			
			product = optionalProducto.get();

			cart.setCantidad(cantidad);
			cart.setPrecio(product.getPrice());
			cart.setNombre(product.getNameProduct());
			cart.setTotal(product.getPrice() * cantidad);
			cart.setProduct(product);
			
			//validar que le producto no se añada 2 veces
			Long idProducto = product.getIdProduct();
			boolean ingresado=detalles.stream().anyMatch(p -> p.getProduct().getIdProduct()==idProducto);
			
			if (!ingresado) {
				detalles.add(cart);
			}
			
			sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

			order.setTotal(sumaTotal);
			model.addAttribute("cart", detalles);
			model.addAttribute("order", order);

			return "cart/view-cart";
		}

		
		@GetMapping("/delete/cart/{id}")
		public String deleteProductoCart(@PathVariable Long id, Model model) {

			List<Cart> ordenesNueva = new ArrayList<Cart>();

			for (Cart detalleOrden : detalles) {
				if (detalleOrden.getProduct().getIdProduct() != id) {
					ordenesNueva.add(detalleOrden);
				}
			}

			detalles = ordenesNueva;

			double sumaTotal = 0;
			sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

			order.setTotal(sumaTotal);
			model.addAttribute("cart", detalles);
			model.addAttribute("orden", order);

			return "cart/view-cart";
		}
		
		
		
		@GetMapping("/showCart")
		public String getCart(Model model, HttpSession session) {
			
			model.addAttribute("cart", detalles);
			model.addAttribute("orden", order);
			
			
			model.addAttribute("sesion", session.getAttribute("userId"));
			return "/cart/view-cart";
		}
		
		
		
		@GetMapping("/order")
		public String order(Model model, HttpSession session) {
			
			User usuario =userService.findById( Long.parseLong(session.getAttribute("userId").toString())).get();
			
			model.addAttribute("cart", detalles);
			model.addAttribute("order", order);
			model.addAttribute("user", usuario);
			
			return "cart/order";
		}
		
		
		
		// guardar la orden
		@GetMapping("/saveOrder")
		public String saveOrder(HttpSession session ) {
			Date fechaCreacion = new Date();
			order.setDateOrder(fechaCreacion);
			
			//usuario
			User usuario =userService.findById( Long.parseLong(session.getAttribute("userId").toString())).get();
			
			order.setUser(usuario);
			order.setStatus("Procesado");
			orderService.save(order);
			
			//guardar detalles
			for (Cart dt:detalles) {
				dt.setOrder(order);
				cartService.save(dt);
			}
			
			///limpiar lista y orden
			order = new Order();
			detalles.clear();
			
			return "redirect:/order/showOrders";
		}
	 
}
