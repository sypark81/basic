package com.maria.thymeleaf;

import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import lombok.extern.slf4j.Slf4j;

import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

/**
 * @author 상용
 * thymeleaf layout 
 */
@Slf4j
@Configuration
public class ThymeleafConfig {
	
	 @Bean 
	 public TemplateResolver templateResolver(){ 
	     ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(); 
	     templateResolver.setPrefix("/views/"); 
	     templateResolver.setSuffix(".html"); 
	     templateResolver.setTemplateMode("LEGACYHTML5"); 
	     templateResolver.setCharacterEncoding("UTF-8");
	     templateResolver.setCacheable(false); 
	     return templateResolver; 
	 } 


	@Bean
	public SpringTemplateEngine templateEngine(){
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.addDialect(new LayoutDialect());;
		return templateEngine;
	}
		
	
	
	
	@Bean 
	public ViewResolver viewResolver(){ 
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver() ; 
		viewResolver.setTemplateEngine(templateEngine()); 
		viewResolver.setCharacterEncoding("UTF-8");
		viewResolver.setOrder(2); 
		log.info("viewResolver...");
		return viewResolver; 
	} 

	
}
