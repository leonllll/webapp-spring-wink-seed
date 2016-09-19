package com.leonlu.code.sample.webapp.ws.aspect;

import java.util.Locale;

import javax.ws.rs.core.Response;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import com.leonlu.code.sample.webapp.ws.common.WebAppException;
import com.leonlu.code.sample.webapp.ws.common.WebAppResponse;

@Aspect
@Component
public class ExceptionAspect {
	@Autowired
	MessageSource messageSource;
	public static final Logger logger = LoggerFactory.getLogger(ExceptionAspect.class);
			
	@Pointcut("within(com.leonlu.code.sample.webapp.ws.resource..*)")
	public void inResourceLayer() {}
	
	@Around("com.leonlu.code.sample.webapp.ws.aspect.ExceptionAspect.inResourceLayer()")
	public Object doExceptionHandling(ProceedingJoinPoint pjp) {
		try {
			return pjp.proceed();
		} catch(WebAppException e) {
			String errorMessage = messageSource.getMessage(e.getErrorCode(), null,
					Locale.getDefault());
			return Response.status(e.getHttpStatus())
					.entity(new WebAppResponse(false, errorMessage).toString()).build();
		} catch (Throwable e) {
			logger.error("internal error", e);
			return Response.serverError()
					.entity(new WebAppResponse(false, e.getMessage()).toString()).build();
		}
	}
}
