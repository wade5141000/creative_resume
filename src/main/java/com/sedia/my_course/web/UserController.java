package com.sedia.my_course.web;

import com.sedia.my_course.dto.GenericResponse;
import com.sedia.my_course.dto.PasswordDto;
import com.sedia.my_course.entity.user.User;
import com.sedia.my_course.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	final UserService userService;

	@GetMapping("/users")
	public List<User> getUsers(){
		return userService.getUsers();
	}

	@PostMapping("/resetPassword")
	public @ResponseBody
	GenericResponse resetPassword(@RequestParam("email") String email, HttpServletRequest request) {
		userService.createPasswordResetTokenForUser(email, request.getContextPath());
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
		request.setAttribute("message", "token錯誤或已被使用。");
		System.out.printf("Invalid token:%s%n", token);
		return "forward:/error";
	}

	@PostMapping("/savePassword")
	public String savePassword(PasswordDto passwordDto, HttpServletRequest request) {
		if(userService.validatePasswordResetToken(passwordDto.getToken())) {
			userService.changeUserPassword(passwordDto);
			return "redirect:/";
		}
		request.setAttribute("message", "token錯誤或已被使用。");
		System.out.printf("Invalid token:%s%n", passwordDto.getToken());
		return "forward:/error";
	}


}
