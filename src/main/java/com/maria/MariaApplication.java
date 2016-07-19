package com.maria;

import java.nio.charset.Charset;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.maria.exception.ErrorConfig;

/**
 * @author 상용
 * spring boot 애플리케이션은  @SpringBootApplication 어노테이션이 정의되어 있는 클래스를 기준으로 컴포넌트 스캔하게 되어있다. 
 * 이중 @EnableAutoConfiguration 어노테이션은 spring boot에 대한 자동설정을 가능하게 해주는데 myBatis를 사용하기 위해서는 DataSource에 대한 구현체를 직접등록해야하기 때문에 
 * 아래와 같이 Datasource 및 TransactionManager에 대한 자동설정을 제외하여야 한다. 
 * 
 * */
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceTransactionManagerAutoConfiguration.class, DataSourceAutoConfiguration.class
,GroovyTemplateAutoConfiguration.class, SecurityAutoConfiguration.class, ErrorMvcAutoConfiguration.class } )
@ComponentScan(basePackages = "com.maria")
//@SpringBootApplication
public class MariaApplication extends SpringBootServletInitializer{
	
	@Override 
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) { 
		//set register error pagefilter false
        //setRegisterErrorPageFilter(false);
 		return application.sources(MariaApplication.class); 
	} 


	public static void main(String[] args) {
		SpringApplication.run(MariaApplication.class, args);
	}

	@Bean
	public HttpMessageConverter<String> responseBodyConverter() {
	    return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }
	 
	@Bean
	public Filter characterEncodingFilter() {
	        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
	        characterEncodingFilter.setEncoding("UTF-8");
	        characterEncodingFilter.setForceEncoding(true);
	        return characterEncodingFilter;
	    }

	@Bean
	public ServerProperties getServerProperties(){
		return new ErrorConfig();
	}

}


