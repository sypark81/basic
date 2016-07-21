package com.maria.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maria.test.domain.Menu;
import com.maria.test.domain.User;
import com.maria.test.repository.MenuMapper;
import com.maria.test.repository.UserMapper;

@Service
public class MenuService {
	
	@Autowired
	private MenuMapper menuMapper;
	
	public List<Menu> selectList(){		
		return menuMapper.selectList();
	}
	
}

