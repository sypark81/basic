package com.maria.test.repository;

import java.util.List; 
 
 
import org.apache.ibatis.annotations.Param;  
import com.maria.test.domain.User; 
 

public interface UserMapper { 
 	public List<User> selectList(); 
 	public User selectOne(String id); 
 	public User selectByUserName(@Param("userName") String userName); 
 	public int insert(User user); 
 	public int update(User user); 
 	public int delete(User user); 
} 
