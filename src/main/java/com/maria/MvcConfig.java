package com.maria;

import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class MvcConfig {
 	
	@Bean 
 	public DispatcherServlet dispatcherServlet() { 
		DispatcherServlet ds = new DispatcherServlet();
        ds.setThrowExceptionIfNoHandlerFound(true);

 		return ds;
 	}

}
 
 
 
 
 
 
 
