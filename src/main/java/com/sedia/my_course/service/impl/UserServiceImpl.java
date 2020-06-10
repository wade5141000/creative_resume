package com.sedia.my_course.service.impl;

import com.sedia.my_course.dao.UserDao;
import com.sedia.my_course.model.user.User;
import com.sedia.my_course.model.user.UserRole;
import com.sedia.my_course.service.UserService;
import lombok.SneakyThrows;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

	@Override
	@SneakyThrows
    public void addNewUser(User user){
		if(userDao.getUserByAccount(user.getAccount()) == null){
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			List<UserRole> authorities = new ArrayList<>();
			authorities.add(new UserRole("GENERAL"));
			user.setAuthorities(authorities);
			userDao.save(user);
		}else{
			throw new Exception("account already exists");
		}
    }

	@Override
    public Iterable<User> getAllUsers(){
        return userDao.findAll();
    }

}
