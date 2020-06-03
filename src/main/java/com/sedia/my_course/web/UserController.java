package com.sedia.my_course.web;

import com.sedia.my_course.model.user.User;
import com.sedia.my_course.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public @ResponseBody Iterable<User> getAllUsers(Model model,User user) {
	    System.out.println("all");
        return userService.getAllUsers();
    }

    @GetMapping("/get/{id}")
    public @ResponseBody User getAllUsers(@PathVariable String id) {
        return userService.getUserById(id);
    }

}
