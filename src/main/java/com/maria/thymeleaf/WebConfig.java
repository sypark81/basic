package com.maria.thymeleaf;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.maria")
@PropertySource("classpath:application.properties")
public class WebConfig extends WebMvcConfigurerAdapter {
 
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
		log.info("defaultServletHandling...");
		
	}
	
	@Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        //WEB-INF 밑에 해당 폴더에서 properties를 찾는다.
        messageSource.setBasename("classpath:META-INF/properties/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(5);
        return messageSource;
    }
 
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor localeChangeInterceptor=new LocaleChangeInterceptor();
        //request로 넘어오는 language parameter를 받아서 locale로 설정 한다.
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }
 
    @Bean(name = "localeResolver")
    public LocaleResolver sessionLocaleResolver(){
        //세션 기준으로 로케일을 설정 한다.
        SessionLocaleResolver localeResolver=new SessionLocaleResolver();
        //쿠키 기준(세션이 끊겨도 브라우져에 설정된 쿠키 기준으로)
//        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
 
        //최초 기본 로케일을 강제로 설정이 가능 하다.
        localeResolver.setDefaultLocale(new Locale("ko_KR"));
        return localeResolver;
    }
 
    public void addInterceptors(InterceptorRegistry registry) {
        //Interceptor를 추가 한다.
        registry.addInterceptor(localeChangeInterceptor());
    }

}
