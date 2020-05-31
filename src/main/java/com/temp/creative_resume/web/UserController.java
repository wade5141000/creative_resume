package com.temp.creative_resume.web;

import com.temp.creative_resume.model.User;
import com.temp.creative_resume.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/add")
    public @ResponseBody String addNewUser(User user) {
        return userService.addNewUser(user);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<User> getAllUsers(Authentication authentication, Principal principal) {
        return userService.getAllUsers();
    }

    @GetMapping("/get/{id}")
    public @ResponseBody User getAllUsers(@PathVariable String id) {
        return userService.getUserById(id);
    }

}
