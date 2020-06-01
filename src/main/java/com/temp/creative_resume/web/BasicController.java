package com.temp.creative_resume.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(){
    	return "login";
    }

    @GetMapping("/login_error")
	public String errorPage(){
		return "error";
    }

    @GetMapping("/logout_finish")
	public String logoutPage(){
    	return "logout";
    }


}
