package com.maria.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maria.file.domain.File;
import com.maria.file.domain.FileSearch;
import com.maria.file.repository.FileMapper;

@Service
public class FileService {

	@Autowired
	private FileMapper fileMapper;
	
	
	public String selectMaxFileId(){
		return fileMapper.selectMaxFileId();
	}
	
	public int insert(File file){
		return fileMapper.insert(file);
	}
	
	public File selectOne(FileSearch fileSearch){
		
		return fileMapper.selectOne(fileSearch);
	}
}
