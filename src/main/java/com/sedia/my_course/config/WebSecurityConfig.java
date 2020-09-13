package com.sedia.my_course.config;

import com.sedia.my_course.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	final CustomUserDetailService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String[] needAuthUrls = {"/course/**", "/course-table/**", "/dashboard/**"};
		http.authorizeRequests()
			.antMatchers(needAuthUrls).authenticated()
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/user/login")
			.loginProcessingUrl("/user/login.do")
			.defaultSuccessUrl("/")
			.failureUrl("/user/login?error=true")
			.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) {
		String[] ignore = {"/webjars/**/*", "/css/**", "/js/**", "/vendor/**"};
		web.ignoring().antMatchers(ignore);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
