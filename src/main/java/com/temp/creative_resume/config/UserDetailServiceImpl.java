package com.temp.creative_resume.config;


import com.temp.creative_resume.dao.UserDao;
import com.temp.creative_resume.model.user.User;
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
		User userByAccount = userDao.getUserByUsername(username);
		if(userByAccount == null){
			throw new UsernameNotFoundException("user not found");
		}
		return userByAccount;
	}
}
