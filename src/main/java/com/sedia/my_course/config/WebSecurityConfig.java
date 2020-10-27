package com.sedia.my_course.config;

import com.sedia.my_course.security.CustomAuthenticationProvider;
import com.sedia.my_course.security.JWTAuthenticationFilter;
import com.sedia.my_course.security.LoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	final CustomAuthenticationProvider customAuthenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()).and()
		.authorizeRequests().antMatchers("/login").permitAll().and()
		.authorizeRequests().anyRequest().authenticated().and()
		.addFilterBefore(new LoginFilter("/login", authenticationManager()),
		UsernamePasswordAuthenticationFilter.class)// 添加過濾器，針對/login的請求，交給LoginFilter處理
		// 添加過濾器，針對其他請求進行JWT的驗證
		.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
		.csrf().disable();

//		String[] needAuthUrls = {"/course/**", "/course-table/**", "/dashboard/**"};
//		http.authorizeRequests()
//			.antMatchers(needAuthUrls).authenticated()
//			.anyRequest().permitAll()
//			.and()
//			.formLogin()
//			.loginPage("/user/login")
//			.loginProcessingUrl("/user/login.do")
//			.defaultSuccessUrl("/")
//			.failureUrl("/user/login?error=true")
//			.and()
//			.logout()
//			.logoutUrl("/logout")
//			.logoutSuccessUrl("/");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(customAuthenticationProvider);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
