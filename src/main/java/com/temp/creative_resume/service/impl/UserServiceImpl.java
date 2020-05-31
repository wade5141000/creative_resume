package com.temp.creative_resume.service.impl;

import com.temp.creative_resume.dao.UserDao;
import com.temp.creative_resume.model.User;
import com.temp.creative_resume.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

	@Override
    public String addNewUser(User user){
        User n = new User();
//        n.setName("name1");
//        n.setEmail("email1");
        n.setUsername(user.getUsername());
		n.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

//        List<Curriculum> list = new ArrayList<>();
//        Curriculum course1 = new Curriculum();
//        course1.setName("c1");
//        course1.setTotalUnits(1);
//        Curriculum course2 = new Curriculum();
//        course2.setName("c2");
//        course2.setTotalUnits(2);
//        list.add(course1);
//        list.add(course2);
//        n.setCurricula(list);
        userDao.save(n);
//        n.setName("new name");
        return "Saved";
    }

	@Override
    public Iterable<User> getAllUsers(){
        return userDao.findAll();
    }

    @Override
    public User getUserById(String id){
        return userDao.getUserByUserId(Integer.parseInt(id));
    }
}
