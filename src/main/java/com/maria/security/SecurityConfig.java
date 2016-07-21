/**
 * 
 */
package com.maria.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 상용
 *
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	/*
	 * web의 특정 요청에 대해 시큐리티 설정을 무시하도록 설정
	 */
	@Override
	public void configure(WebSecurity web){
		web.ignoring().antMatchers("/css/**","/js/**","/i18n/**","/img/**");
		log.info("add web ignore path");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//인가관련설정, login화면 및 register(가입하기)화면은 모든 사용자가 접속할 수 있도록 설정 그 외의 경로는 인증 없이 접속불가
		http.authorizeRequests() 
	        .antMatchers("/", "/member/**").permitAll()
	        .antMatchers("/menu/**","/admin/**", "/secure/**","/download/**").hasRole("ADMIN")
	        .antMatchers("/menu/**","/download/**").hasRole("USER")
	        .anyRequest().authenticated();
		    
		
		//로그인 폼 인증 처리 유효화 및 인증처리 경로, 로그인 처리 경로, 로그인 경로, 인증실패시 경로, 인증성공시 경로, 사용자 이름과, 암호 관련 파라미터를 설정
		http.formLogin()
			.loginProcessingUrl("/login")
			.loginPage("/loginForm")
			.failureUrl("/loginForm?error")
			.defaultSuccessUrl("/member/userFind", true)
			.usernameParameter("userName")
			.passwordParameter("password")
			.permitAll()
			.and();
		
		//로그아웃 설정
		http.logout() 
		    .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
		    .logoutSuccessUrl("/loginForm")
		    .and()
		    .csrf().disable()
		    .httpBasic();
		
		log.info("config login,out");
	}
	
	
	/*
	 * GlobalAuthenticationConfigureAdapder를 상속한 javaConfig 클래스에서 인증 처리에 관련된 사항들을 설정
	 */
	@Configuration
	static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter{
		
		@Autowired
		UserDetailsService userDetailsService;
		
		/*
		 * 암호를 해시 형식으로 만들기위한 PasswordEncoder class 정의 여기서 BCrypt를 사용
		 */
		@Bean
		PasswordEncoder passwordEncoder(){
			return new BCryptPasswordEncoder(); 
		}
		/**
		 * 인증처리 관련 설정 인증처리를 위해 사용자를 가져오는 UserDetailsService와 암호를 대조할 때 사용하는 PasswordEncoder를 설정함
		 */
		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception{
			auth.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder());
			log.info("security init...");
		}
	}

}
