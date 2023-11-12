package com.arpo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arpo.models.Order;
import com.arpo.models.User;
import com.arpo.service.OrderService;
import com.arpo.service.UserService;
import com.arpo.utils.PDF;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;
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
		
		User usuario= userService.findById(Long.parseLong(session.getAttribute("userId").toString()) ).get();
		List<Order> ordenes= orderService.findByUsuario(usuario);
		
		model.addAttribute("orders", ordenes);
		
		return "client/clientListOrder";
	}
	
	
	@GetMapping("/export/pdf/{id}")
	public void exportToPDF(@PathVariable int id, HttpServletResponse response) throws DocumentException, IOException{
	        response.setContentType("application/pdf");
	        Optional<Order> orderOptional = orderService.findById(id);

	        if (orderOptional.isPresent()) {
	            Order order = orderOptional.get();
	            System.out.print("si entro");
	            String headerKey = "Content-Disposition";
	            String headerValue = "attachment; filename=factura_"+ order.getUser().getName()+
	            		"_"+id+".pdf";
	            response.setHeader(headerKey, headerValue);

	            PDF exporter = new PDF(order);
	            exporter.export(response);
	        } else {
	            response.getWriter().println("La orden no fue encontrada.");
	            
	        }
	}

}

