package com.sedia.my_course.web;

import com.sedia.my_course.model.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalController {

	// 登入使用者加到model
	@ModelAttribute
	public void addAttributes(Model model, Authentication authentication){
		if(authentication != null){
			User user = (User) authentication.getPrincipal();
			model.addAttribute("user",user);
		}
	}

	@ExceptionHandler(Throwable.class)
	public String exceptionHandler(Throwable exception){
		exception.printStackTrace();
		return "error/error_page";
	}
}
