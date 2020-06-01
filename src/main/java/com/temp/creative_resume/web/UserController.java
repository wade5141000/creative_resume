package com.temp.creative_resume.web;

import com.temp.creative_resume.model.user.User;
import com.temp.creative_resume.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/add")
    public @ResponseBody String addNewUser(User user) {
        return userService.addNewUser(user);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<User> getAllUsers(Authentication authentication) {
	    System.out.println(((User)authentication.getPrincipal()).getUserRealName());
        return userService.getAllUsers();
    }

    @GetMapping("/get/{id}")
    public @ResponseBody User getAllUsers(@PathVariable String id) {
        return userService.getUserById(id);
    }

}
