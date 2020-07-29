package com.sedia.my_course.web;

import com.sedia.my_course.model.user.User;
import com.sedia.my_course.service.UserService;
import com.sedia.my_course.utils.GenericResponse;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;

	@Resource
	JavaMailSender mailSender;

	@Resource
	protected AuthenticationManager authenticationManager;

	@PostMapping("/add")
	public String addNewUser(User user) {
		// 註冊後自動登入
		UsernamePasswordAuthenticationToken token =
			new UsernamePasswordAuthenticationToken(user.getAccount(), user.getPassword());
		// 存入DB
		userService.addNewUser(user);

		authenticationManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(token);
		return "redirect:/";
	}

	@GetMapping("/reset-pw")
	public String changePasswordPage() {
		return "forgotPassword";
	}

	@PostMapping("/resetPassword")
	public @ResponseBody GenericResponse resetPassword(HttpServletRequest request,
	                                     @RequestParam("email") String userEmail) throws Exception {
		User user = userService.findUserByEmail(userEmail);
		if (user == null) {
			throw new Exception();
		}
		userService.createPasswordResetTokenForUser(user, request);

		return new GenericResponse<>("aaa",request.getLocale());
	}

	@GetMapping("/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/send")
	public @ResponseBody void test() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("wade5141000@outlook.com");
		message.setSubject("測試透過 Gmail 去發信");
		message.setText(LocalDateTime.now().toString() + "    透過 Gmail 發信。");
		mailSender.send(message);
	}



}
