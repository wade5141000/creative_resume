package com.sedia.my_course.web;

import com.sedia.my_course.dto.GenericResponse;
import com.sedia.my_course.dto.PasswordDto;
import com.sedia.my_course.entity.user.User;
import com.sedia.my_course.service.UserService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;

	@Resource JavaMailSender mailSender;

	@Resource
	protected AuthenticationManager authenticationManager;

	@GetMapping("/login")
	public String loginPage(Model model){
		model.addAttribute("showNavigator","N");
		model.addAttribute("showFooter","N");
		return "account/login";
	}

	@GetMapping("/signup")
	public String signUp(){
		return "account/signup";
	}

	@PostMapping("/add")
	public String addNewUser(User user) {
		// 註冊後自動登入
		// TODO 註冊email不可重複
		UsernamePasswordAuthenticationToken token =
			new UsernamePasswordAuthenticationToken(user.getAccount(), user.getPassword());
		// 存入DB
		userService.addNewUser(user);

		authenticationManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(token);
		return "redirect:/";
	}

	@GetMapping("/reset-password")
	public String changePasswordPage(Model model) {
		model.addAttribute("showNavigator","N");
		model.addAttribute("showFooter","N");
		return "account/forgotPassword";
	}

	@PostMapping("/resetPassword")
	public @ResponseBody GenericResponse resetPassword(HttpServletRequest request,
	                                     @RequestParam("email") String userEmail) throws Exception {
		User user = userService.getUserByEmail(userEmail);
		if (user == null) {
			throw new Exception();
		}
		userService.createPasswordResetTokenForUser(user, request);
		return new GenericResponse<>("aaa",request.getLocale());
	}

	@GetMapping("/changePassword")
	public String showChangePasswordPage(Model model, @RequestParam("token") String token) {
		String result = userService.validatePasswordResetToken(token);
		if(result != null) {
			System.out.println("validatePasswordResetToken fail: " + result);
			return "redirect:/error";
		} else {
			model.addAttribute("token", token);
			return "updatePassword";
		}
	}

	@PostMapping("/savePassword")
	public String savePassword(PasswordDto passwordDto) {
		String result = userService.validatePasswordResetToken(passwordDto.getToken());

		if(result != null) {
			System.out.println("validatePasswordResetToken fail: " + result);
			return "redirect:/error";
		}
		userService.changeUserPassword(passwordDto);
		return "redirect:/";
	}

	@GetMapping("/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userService.getAllUsers();
	}



}
