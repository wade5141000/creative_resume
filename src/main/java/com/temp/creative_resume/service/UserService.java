package com.temp.creative_resume.service;

import com.temp.creative_resume.dao.UserDao;
import com.temp.creative_resume.model.Curriculum;
import com.temp.creative_resume.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("UserService")
public class UserService {

    @Resource
    private UserDao userDao;

    public String addNewUser(){
        User n = new User();
        n.setName("name1");
        n.setEmail("email1");

        List<Curriculum> list = new ArrayList<>();
        Curriculum course1 = new Curriculum();
        course1.setName("c1");
        course1.setTotalUnits(1);
        Curriculum course2 = new Curriculum();
        course2.setName("c2");
        course2.setTotalUnits(2);
        list.add(course1);
        list.add(course2);
        n.setCurricula(list);
        userDao.save(n);
        n.setName("new name");
        return "Saved";
    }

    public Iterable<User> getAllUsers(){
        return userDao.findAll();
    }

    public User getUserById(String id){
        return userDao.getUserById(Integer.parseInt(id));
    }
}
