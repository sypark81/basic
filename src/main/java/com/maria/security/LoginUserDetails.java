package com.maria.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

/**
 * @author 상용
 * 스프링 시큐리티로 인증시 인증 사용자 인터페이스인 org.springframework.security.core.userdetails.UserDetails의 사용자 이름을 사용해서
 * 해당 인증 사용자를 가져오는 인턴페이스인 org.springframework.security.core.userdetails.UserDetailsService를 사용합니다.
 * 스프링 시큐리티에는 UserDetails와 UserDetailsService가 기본으로 마련되어 있으며, 확장성을 고려하여 User 클래스와 UserRepository 클래스를
 * 이용하여 이들을 구현한다.
 */
@Data
public class LoginUserDetails extends User{
		
	private final com.maria.test.domain.User user;
	
	/*
	 * user domain에 대한 UserDetails 생성자
	 * o.s.s.c.u.User 클래스의 생성자를 사용하여 '사용자 이름', '암호', '허가작업'을 할 수 있는 역할을 설정합니다.
	 */
	public LoginUserDetails(com.maria.test.domain.User user){
		super(user.getUserName(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
		this.user = user;
		
	}
	
}
