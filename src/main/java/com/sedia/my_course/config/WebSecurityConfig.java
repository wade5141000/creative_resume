package com.sedia.my_course.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource(name = "customUserDetailsService")
	private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//		直接通過security，無須驗證
//	    http.authorizeRequests()
//		    .antMatchers("/**")
//		    .permitAll();
//        http.formLogin() // 定義當需要提交表單進行用戶登錄時候，轉到的登錄頁面。
//	        .and()
//	        .authorizeRequests() // 定義哪些URL需要被保護、哪些不需要被保護
//	        .anyRequest() // 任何請求,登錄後可以訪問
//	        .authenticated();

	    http.formLogin()
		    .loginPage("/login?flag=1")
		    .loginProcessingUrl("/user/login")
		    .defaultSuccessUrl("/").permitAll()
		    .failureUrl("/login_error")
		    .and()
		    .logout()
		    .logoutUrl("/logout")
		    .logoutSuccessUrl("/logout_success")
		    .and()
		    .authorizeRequests()
		    .antMatchers("/", "/user/login", "/add", "/login", "/logout_success", "/signup").permitAll()
		    .anyRequest().authenticated()
		    .and().csrf().disable();

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
	public void configure(WebSecurity web) throws Exception {
    	web.ignoring().antMatchers("/webjars/**/*", "/**/*.css", "/**/*.js");
    }

}
