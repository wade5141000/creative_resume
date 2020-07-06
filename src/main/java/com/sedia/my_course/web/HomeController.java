package com.sedia.my_course.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping({"/", "/index"})
	public String index(){
		return "index";
	}

	@GetMapping("/test")
	public String test(Model model){
		throw new RuntimeException();
	}
}
