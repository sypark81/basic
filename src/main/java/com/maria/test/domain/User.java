package com.maria.test.domain;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class User implements Serializable{ 
 
 
 	private String id; 
 	private String userName; 
 	private String password; //encoded된 암호를 사용
 	
 	@JsonIgnore
    private List<MultipartFile> file;

    private String photoFileName;
    
    private String photoFileId;

 	
 } 

