package com.maria.file.repository;

import java.util.List; 
 
 
import org.apache.ibatis.annotations.Param;

import com.maria.file.domain.File;
import com.maria.file.domain.FileSearch;
import com.maria.test.domain.User;
 

public interface FileMapper { 
	public String selectMaxFileId();
 	public int insert(File file); 
 	public int update(File file); 
 	public int delete(File file); 
 	public List<File> selectList(); 
 	public File selectOne(FileSearch filesearch); 

} 
