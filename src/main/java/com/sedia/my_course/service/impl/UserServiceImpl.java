package com.sedia.my_course.service.impl;

import com.sedia.my_course.dao.UserDao;
import com.sedia.my_course.model.user.UserRole;
import com.sedia.my_course.service.UserService;
import com.sedia.my_course.model.user.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

	@Override
    public String addNewUser(User user){
		user.setNickname("my name: "+ UUID.randomUUID());
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		List<UserRole> authorities = new ArrayList<>();
		authorities.add(new UserRole("GENERAL"));
		user.setAuthorities(authorities);
		try {
			userDao.save(user);
		}catch (Exception e){
			e.printStackTrace();
		}
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
