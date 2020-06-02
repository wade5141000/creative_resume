package com.temp.creative_resume.web;

import com.temp.creative_resume.model.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {


	@ModelAttribute
	public void addAttributes(Model model, Authentication authentication){
		if(authentication != null){
			User user = (User) authentication.getPrincipal();
			model.addAttribute("user",user);
		}
	}
}
