package com.sedia.my_course.config;

import com.sedia.my_course.service.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	private CustomUserDetailService userDetailsService;

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
			.failureUrl("/error") // TODO 換成登入失敗畫面
			.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/")
			.and();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) {
		String[] ignore = {"/webjars/**/*", "/css/**", "/js/**", "/vendor/**"};
		web.ignoring().antMatchers(ignore);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
