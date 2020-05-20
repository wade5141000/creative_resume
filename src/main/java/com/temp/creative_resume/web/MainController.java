package com.temp.creative_resume.web;


import com.temp.creative_resume.dao.UserRepository;
import com.temp.creative_resume.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public @ResponseBody String index(){
        return "index";
    }

    @GetMapping("/add")
    public @ResponseBody String addNewUser () {
        User n = new User();
        n.setName("name1");
        n.setEmail("email1");
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

}
