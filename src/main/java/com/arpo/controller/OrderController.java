package com.arpo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arpo.models.Order;
import com.arpo.models.User;
import com.arpo.service.OrderService;
import com.arpo.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	public OrderService orderService;
	
	@Autowired
	public UserService userService;
	
	
	@GetMapping("/showOrders")
	public String obtenerCompras(Model model, HttpSession session) {
		model.addAttribute("sesion", session.getAttribute("userId"));
		
		User usuario= userService.findById(  Long.parseLong(session.getAttribute("userId").toString()) ).get();
		List<Order> ordenes= orderService.findByUsuario(usuario);
		
		model.addAttribute("orders", ordenes);
		
		return "client/clientListOrder";
	}

}

