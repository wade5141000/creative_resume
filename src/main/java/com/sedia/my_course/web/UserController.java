package com.sedia.my_course.web;

import com.sedia.my_course.dto.GenericResponse;
import com.sedia.my_course.dto.PasswordDto;
import com.sedia.my_course.entity.user.User;
import com.sedia.my_course.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

	final UserService userService;

	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("showTopbar", "N");
		model.addAttribute("showSidebar", "N");
		model.addAttribute("showFooter", "N");
		return "account/login";
	}

	@GetMapping("/signup")
	public String signUp(Model model) {
		model.addAttribute("showTopbar", "N");
		model.addAttribute("showSidebar", "N");
		model.addAttribute("showFooter", "N");
		return "account/signup";
	}

	@PostMapping("/add")
	public String addNewUser(User user) {
		userService.addNewUser(user);
		return "redirect:/user/login";
	}

	@GetMapping("/reset-password")
	public String changePasswordPage(Model model) {
		model.addAttribute("showTopbar", "N");
		model.addAttribute("showSidebar", "N");
		model.addAttribute("showFooter", "N");
		return "account/forgotPassword";
	}

	@PostMapping("/resetPassword")
	public @ResponseBody
	GenericResponse resetPassword(HttpServletRequest request,
	                              @RequestParam("email") String userEmail) {
		User user = userService.getUserByEmail(userEmail);
		userService.createPasswordResetTokenForUser(user, request);
		// FIXME return
		return new GenericResponse<>("已成功發送重置密碼信件", request.getLocale());
	}

	@GetMapping("/changePassword")
	public String showChangePasswordPage(Model model, @RequestParam("token") String token, HttpServletRequest request) {
		if (userService.validatePasswordResetToken(token)) {
			model.addAttribute("token", token);
			model.addAttribute("showTopbar", "N");
			model.addAttribute("showSidebar", "N");
			model.addAttribute("showFooter", "N");
			return "account/updatePassword";
		}
		request.setAttribute("message", "token有誤，請重新操作");
		log.error("Invalid token:{}", token);
		return "redirect:/error";
	}

	@PostMapping("/savePassword")
	public String savePassword(PasswordDto passwordDto) {
		if(userService.validatePasswordResetToken(passwordDto.getToken())) {
			userService.changeUserPassword(passwordDto);
			return "redirect:/";
		}
		log.error("Invalid token:{}", passwordDto.getToken());
		return "redirect:/error";
	}


}
