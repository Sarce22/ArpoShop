package com.arpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arpo.service.OrderService;
import com.arpo.service.UserService;


@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	public OrderService orderService;
	
	@Autowired
	public UserService userService;
	
	 @GetMapping("/listOrder")
	    public String listOrder(Model model) {
	     model.addAttribute("ListOrder", orderService.findAll());
	     return "client/clientListOrder";
	    }

}
