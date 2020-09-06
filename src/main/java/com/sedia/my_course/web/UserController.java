package com.sedia.my_course.web;

import com.sedia.my_course.dto.GenericResponse;
import com.sedia.my_course.dto.PasswordDto;
import com.sedia.my_course.entity.user.User;
import com.sedia.my_course.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

	final UserService userService;
	final AuthenticationManager authenticationManager;

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
		// TODO 註冊email不可重複
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
	                              @RequestParam("email") String userEmail) throws Exception {
		User user = userService.getUserByEmail(userEmail);
		if (user == null) {
			throw new Exception();
		}
		userService.createPasswordResetTokenForUser(user, request);
		return new GenericResponse<>("aaa", request.getLocale());
	}

	@GetMapping("/changePassword")
	public String showChangePasswordPage(Model model, @RequestParam("token") String token) {
		String result = userService.validatePasswordResetToken(token);
		if (result != null) {
			log.error("validatePasswordResetToken fail: " + result);
			return "redirect:/error";
		} else {
			model.addAttribute("token", token);
			model.addAttribute("showTopbar", "N");
			model.addAttribute("showSidebar", "N");
			model.addAttribute("showFooter", "N");
			return "account/updatePassword";
		}
	}

	@PostMapping("/savePassword")
	public String savePassword(PasswordDto passwordDto) {
		String result = userService.validatePasswordResetToken(passwordDto.getToken());

		if (result != null) {
			log.error("validatePasswordResetToken fail: " + result);
			return "redirect:/error";
		}
		userService.changeUserPassword(passwordDto);
		return "redirect:/";
	}

	@GetMapping("/all")
	public @ResponseBody List<User> getAllUsers() {
		return userService.getAllUsers();
	}


}
