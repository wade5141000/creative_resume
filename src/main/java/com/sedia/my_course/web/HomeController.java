package com.sedia.my_course.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {

	@GetMapping("/privacy")
	public String privacyPage(Model model) {
		model.addAttribute("showSidebar", "N");
		return "policy/privacy";
	}

	@GetMapping("/terms")
	public String termsPage(Model model) {
		model.addAttribute("showSidebar", "N");
		return "policy/terms";
	}

	@GetMapping("/test")
	public String test(Model model) {
		model.addAttribute("showNavigator", "N");
		model.addAttribute("showFooter", "N");
		return "redirect:/user/login";
	}

	@GetMapping("/test2")
	public String test2(Model model) {
		log.info("test2");
		Object obj = null;
		obj.equals("xxx");
		return "index";
//		throw new ResponseStatusException(HttpStatus.FORBIDDEN, "你沒有權限");
	}

}
