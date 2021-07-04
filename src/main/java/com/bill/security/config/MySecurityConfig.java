package com.bill.security.config;

import com.bill.security.filter.JwtRequestFilter;
import com.bill.security.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService myUserDetailsService;

    private final JwtRequestFilter jwtRequestFilter;

    public MySecurityConfig(MyUserDetailsService myUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.myUserDetailsService = myUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/authentication")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

//    ===========舊版本 沒JWT 純 spring-security=============
    //    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // TODO Auto-generated method stub
////		super.configure(http);
//
//        //自訂權限規則
//        http.authorizeRequests().antMatchers("/").permitAll()
//                .antMatchers("/level1/**").hasRole("VIP1")
//                .antMatchers("/level2/**").hasRole("VIP2")
//                .antMatchers("/level3/**").hasRole("VIP3");
//
//        //開啟auto config的登入功能
//        http.formLogin().usernameParameter("user").passwordParameter("pwd").loginPage("/user");
//        //進登入頁
//        ///login?error 表示失敗
//
//
////		http.logout();
//        http.logout().logoutSuccessUrl("/");//登出後回首頁
//
//        http.rememberMe().rememberMeParameter("remember");
//    }


//    //自訂義驗證規則
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // TODO Auto-generated method stub
////		super.configure(auth);
//        UserDetails user = myUserDetailsService.loadUserByUsername("Bill");
//
//        auth.inMemoryAuthentication()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser(user.getUsername())
//                .password(new BCryptPasswordEncoder().encode(user.getPassword()))
//                .roles("VIP1", "VIP2")
//
//                .and()
//
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("paul")
//                .password(new BCryptPasswordEncoder().encode("pauljay"))
//                .roles("VIP1");
//    }


}
