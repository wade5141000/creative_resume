package com.sedia.my_course.web;

import com.sedia.my_course.model.user.User;
import com.sedia.my_course.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

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

    @GetMapping("/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
