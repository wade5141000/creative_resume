package com.temp.creative_resume.web;

import com.temp.creative_resume.model.User;
import com.temp.creative_resume.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/add")
    public @ResponseBody String addNewUser(User user) {
	    System.out.println(user);
        return userService.addNewUser(user);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/get/{id}")
    public @ResponseBody User getAllUsers(@PathVariable String id) {
        return userService.getUserById(id);
    }

}
