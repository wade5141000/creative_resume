package com.temp.creative_resume.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(){
    	return "login";
    }

//	@RequestMapping("/user/login")
//	public String login(@RequestParam String username, @RequestParam String password){
//
//		System.out.println("aaaa");
//		System.out.println(username);
//		System.out.println(password);
//		return null;
//	}

}
