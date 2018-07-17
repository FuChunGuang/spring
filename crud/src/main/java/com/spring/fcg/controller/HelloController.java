package com.spring.fcg.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	@RequestMapping("/hello")
	public String hello() {
		return "html/hello";
	}
	@RequestMapping("/demo")
	public String demo(Map<String, String> map) {
		map.put("demo", "11111");
		return "demo";
	}
}
