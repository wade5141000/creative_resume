package com.temp.creative_resume.web;

import com.temp.creative_resume.model.User;
import com.temp.creative_resume.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/add")
    public @ResponseBody String addNewUser () {
        return userService.addNewUser();
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
