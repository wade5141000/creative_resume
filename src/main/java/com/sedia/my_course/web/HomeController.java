package com.sedia.my_course.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class HomeController {

	@GetMapping({"/", "/index"})
	public String index(){
		return "index";
	}

	@GetMapping("/test")
	public String test(Model model){
		model.addAttribute("showNavigator", "N");
		model.addAttribute("showFooter", "N");
		return "login";
	}

	@GetMapping("/test2")
	public String test2(Model model){
		System.out.println("test2");
		Object obj = null;
		obj.equals("xxx");
		return "xxx";
	}

	@GetMapping("/test3")
	public String test3(Model model){
		System.out.println("test3");
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, "你沒有權限");
	}

}
