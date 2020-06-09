package com.sedia.my_course.config;


import com.sedia.my_course.dao.UserDao;
import com.sedia.my_course.model.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("customUserDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

	@Resource
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.getUserByAccount(username);
		if(user == null){
			throw new UsernameNotFoundException("user not found");
		}
		return user;
	}
}
