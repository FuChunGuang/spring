package com.spring.fcg.controller;


import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.fcg.entity.User;
import com.spring.fcg.util.BaseController;

@Controller
<<<<<<< HEAD
public class HelloController {

	@RequestMapping("/index")
	public String index() {

=======
public class HelloController extends BaseController{
	@RequestMapping("/hello")
	public String hello() {
		return "html/hello";
	}
	@RequestMapping("/demo")
	public String demo(Map<String, String> map) {
		map.put("demo", "11111");
		return "demo";
	}
	@RequestMapping("/login")
	public String login() {
		
		return "html/login";
	}
	@PostMapping("/index")
	public String index(@Valid User user,BindingResult bindingResult) {
		Map<String,Object> map=getParam();
		System.out.println(map.get("username"));
		System.out.println(map.get("password"));
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
	    if (bindingResult.hasErrors()){
	        System.out.println(bindingResult.getFieldError().getDefaultMessage());
	        return "html/login";
	    }
>>>>>>> branch 'master' of https://github.com/FuChunGuang/spring.git
		return "html/index";
	}

	@RequestMapping("/login")
	public String login() {

		return "html/login";
	}
}
