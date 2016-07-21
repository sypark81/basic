package com.maria.test.repository;

import java.util.List; 
 
 
import org.apache.ibatis.annotations.Param;

import com.maria.test.domain.Menu;
import com.maria.test.domain.User; 
 

public interface MenuMapper { 
 	public List<Menu> selectList(); 
 	public User selectOne(String menuId); 
 	public int insert(Menu menu); 
 	public int update(Menu menu); 
 	public int delete(Menu menu); 
} 
