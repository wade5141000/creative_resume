package com.sedia.my_course.config;


import com.sedia.my_course.repository.UserRepository;
import com.sedia.my_course.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customUserDetailsService")
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

	final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByAccount(username);
		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return user;
	}
}
