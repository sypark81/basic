package com.maria.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileUploadBase.SizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	public static final String DEFAULT_ERROR_VIEW ="error/error";
	
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        
		log.info("exception ocurred...");
		
		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
			log.info("뭔 놈의 익셉션인가?");
			throw e;
			
		}
		
		log.info("에러클래스 : "+e.getClass());
            
		log.error("Request: " + req.getRequestURL() + " raised " + e);
        // Otherwise setup and send the user to a default error-view.
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        log.info("메시지 : "+e.getMessage());
        log.info("로컬메시지 : "+e.getLocalizedMessage());
        
        if(e instanceof MultipartException){
        	
        	mav.addObject("message",messageSource.getMessage("error.sizelimitexceed", null, req.getLocale()));
        }else{
        	 mav.addObject("message",messageSource.getMessage("error.notfound", null, req.getLocale()));
        }
       
        mav.addObject("url", req.getRequestURL());
        mav.addObject("timestamp", new Date().toString());
        mav.addObject("status", -100);
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
	
	

			

}
