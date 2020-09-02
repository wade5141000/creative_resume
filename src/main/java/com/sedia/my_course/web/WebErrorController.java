package com.sedia.my_course.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebErrorController implements ErrorController {

	private static final String ERROR_PATH = "/error";

	@RequestMapping(ERROR_PATH)
	public String handleError(HttpServletRequest request, Model model) {
//		Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//		model.addAttribute("status", statusCode);
		return "error/error_page";
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
}
