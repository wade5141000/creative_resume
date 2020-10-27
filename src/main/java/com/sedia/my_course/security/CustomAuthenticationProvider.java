package com.sedia.my_course.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	final UserLoginService userLoginService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// 獲得使用者帳號及密碼
		String account = authentication.getName();
		String password = authentication.getCredentials().toString();
		UserDetails user = userLoginService.loadUserByUsername(account);
		// 帳號密碼驗證邏輯
		if (account.equals(user.getUsername()) && password.equals(user.getPassword())) {

			// 生成Authentication令牌
			Authentication auth = new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
			return auth;
		} else {
			throw new BadCredentialsException("Password error");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}  