package com.temp.creative_resume.service.impl;


import com.temp.creative_resume.dao.UserDao;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("customUserDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

	@Resource
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		com.temp.creative_resume.model.User userByAccount = userDao.getUserByAccount(userName);
		if(userByAccount == null){
			throw new UsernameNotFoundException("找不到USER");
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ADMIN"));
		return new User(userName, userByAccount.getPassword(), authorities);
	}
}
