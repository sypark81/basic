package com.maria.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.maria.test.domain.User;
import com.maria.test.repository.UserMapper;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Service
public class LoginUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		
		log.info("call loadUserByUsername... \n param userName : "+userName);
		
		User user = userMapper.selectByUserName(userName);
		if(user == null) throw new UsernameNotFoundException("The requested user id not found.");
		
		return new LoginUserDetails(user); 
	}
	
	
}
