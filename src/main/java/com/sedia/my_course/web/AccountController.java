package com.sedia.my_course.web;


import com.sedia.my_course.entity.user.User;
import com.sedia.my_course.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

	final UserService userService;

	@PostMapping("/signup")
	public User signUp(User user) {
		return userService.addNewUser(user);
	}
}
