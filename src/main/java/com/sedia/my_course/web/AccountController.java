package com.sedia.my_course.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

    @GetMapping("/login")
    public String loginPage(Model model, @RequestParam(required = false) String flag){
    	model.addAttribute("flag", flag);
    	return "login";
    }

    @GetMapping("/login_error")
	public String errorPage(){
		return "error";
    }

    @GetMapping("/logout_success")
	public String logoutPage(){
    	return "logout_success";
    }

    @GetMapping("/signup")
	public String signUp(){
    	return "signup";
    }

}
