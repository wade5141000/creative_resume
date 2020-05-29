package com.temp.creative_resume.service.impl;


import com.temp.creative_resume.dao.UserDao;
import com.temp.creative_resume.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("customUserDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

	@Resource
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User userByAccount = userDao.getUserByAccount(userName);
		if(userByAccount == null){
			throw new UsernameNotFoundException("user not found");
		}
		// TODO ROLE去DB撈
		List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN, USER");
		return new User();
	}
}
