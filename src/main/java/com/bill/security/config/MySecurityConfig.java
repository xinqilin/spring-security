package com.bill.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
	}

	 
	
}
