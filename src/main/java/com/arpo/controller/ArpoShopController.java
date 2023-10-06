package com.arpo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArpoShopController {

	
	@GetMapping({"/login"})
	public String login() {
		return "login";
	}
	
	@GetMapping({"/signIn"})
	public String signIn() {
		return "signIn";
	}
}
