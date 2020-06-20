package com.bill.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
//		super.configure(http);
		
		//自訂權限規則
		http.authorizeRequests().antMatchers("/").permitAll()
								.antMatchers("/level1/**").hasRole("VIP1")
								.antMatchers("/level2/**").hasRole("VIP2")
								.antMatchers("/level3/**").hasRole("VIP3");
	
		//開啟auto config的登入功能
		http.formLogin();
		//進登入頁
		///login?error 表示失敗
	}

	
	//自訂義驗證規則
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
//		super.configure(auth);
		auth.inMemoryAuthentication()
			.passwordEncoder(new BCryptPasswordEncoder())
			.withUser("bill")
			.password(new BCryptPasswordEncoder().encode("billisgood"))
			.roles("VIP1","VIP2")
			
			.and()
			
			.passwordEncoder(new BCryptPasswordEncoder())
			.withUser("paul")
			.password(new BCryptPasswordEncoder().encode("pauljay"))
			.roles("VIP1");
	}

	
	
}
