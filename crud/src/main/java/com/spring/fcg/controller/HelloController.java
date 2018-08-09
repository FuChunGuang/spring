package com.spring.fcg.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@RequestMapping("/index")
	public String index() {
		return "html/index";
	}

	@RequestMapping("/login")
	public String login() {

		return "html/login";
	}
}
