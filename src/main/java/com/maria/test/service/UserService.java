package com.maria.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maria.test.domain.User;
import com.maria.test.repository.UserMapper;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public List<User> selectList(){		
		return userMapper.selectList();
	}
	
	public int insert(User user){
		
		
//		if(user.getFile() != null){
//			String fileName = user.getFile().getOriginalFilename();
//			
//			if(fileName.length() != 0){
//				//여기서 파일업로드 로직 수행
//				
//			}
//		}
			 
			
		return userMapper.insert(user);
	}
	
	
	public User selectOne(String id){
		
		return userMapper.selectOne(id);
	}
	
	
	public int updateUser(User user){
		
		return userMapper.update(user);
	}
	
	public int deleteUser(User user){
		
		return userMapper.delete(user);
	}
}

