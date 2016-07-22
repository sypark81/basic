package com.maria.exception;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.http.HttpStatus;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
public class ErrorConfig extends ServerProperties {
	
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
    	log.info("error page add...");
    	super.customize(container);
        container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/views/error/notFoundError.html" ));
        container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/views/error/forbiddenError.html" ));
        container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/views/error/internalError.html"));
        //container.addErrorPages(new ErrorPage("/views/error/error.html"));

    }
 
}
