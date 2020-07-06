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
		System.out.println("test1");
		throw new RuntimeException();
	}

	@GetMapping("/test2")
	public String test2(Model model){
		System.out.println("test2");
		Object obj = null;
		obj.equals("xxx");
		return "xxx";
	}
}
