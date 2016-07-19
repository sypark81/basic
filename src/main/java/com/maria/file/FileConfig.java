package com.maria.file;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import com.maria.file.service.DownLoadImpl;

import lombok.extern.slf4j.Slf4j;



/**
 * @author 상용
 * file viewresolver 
 */
@Slf4j
@Configuration
public class FileConfig {

	
	 
	
	@Bean 
	public ViewResolver fileViewResolver(){ 
		BeanNameViewResolver fileViewResolver = new BeanNameViewResolver() ; 
		
		fileViewResolver.setOrder(1); 
		log.info("File viewResolver...");
		return fileViewResolver; 
	} 
	
	@Bean
	public DownLoadImpl downloadView(){
		return new DownLoadImpl();
		
	}
}
