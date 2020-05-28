package com.temp.creative_resume.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
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
		    .loginPage("/login")
		    .loginProcessingUrl("/user/login")
		    .defaultSuccessUrl("/").permitAll()
		    .and()
		    .authorizeRequests()
		    .antMatchers("/", "/index", "/user/login", "/add").permitAll()
		    .anyRequest().authenticated()
		    .and().csrf().disable();

    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService)
	    .passwordEncoder(passwordEncoder());
//		auth.authenticationProvider(authenticationProvider());

//		auth.inMemoryAuthentication()
//			.passwordEncoder(new BCryptPasswordEncoder())
//			.withUser("admin")
//			.password(new BCryptPasswordEncoder().encode("admin"))
//			.roles("admin");

    }


	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
    	web.ignoring().antMatchers("/webjars/**/*", "/**/*.css", "/**/*.js");
    }

    /**
     * 配置一個userDetailsService Bean
     * 不再生成默認security.user用戶
     */
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//
//    	return super.userDetailsService();
//    }


}
