package com.sedia.my_course.config;


import com.sedia.my_course.repository.UserRepository;
import com.sedia.my_course.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("customUserDetailsService")
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

	final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByAccount(username);
		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return user;
	}
}
