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
public class Menu implements Serializable{ 
 
 
 	private String menuId; 
 	private String menuName; 
 	private String upMenuId; 
 	
 	
 } 

