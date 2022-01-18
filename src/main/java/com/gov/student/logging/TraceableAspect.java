package com.gov.student.logging;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Aspect
@Component
public class TraceableAspect {

	@Autowired(required = false)
	private HttpServletRequest request;
	
	@Around("@annotation(Traceable)")
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable
	{
	
		    System.out.println("Input :\n" + joinPoint.getArgs().length);
	
		    
		    String url = request.getRequestURI();
		    
	        Object result = joinPoint.proceed();
	        
	        String method = request.getMethod();
	 
	        String methodParam = request.getParameter(method) ;
	        
	       
	        
	       // System.out.println(result);
	        
	        log.info("=======================================");
	        log.info("URL {}", url);
	        log.info("METHOD {}", method);
	        log.info("METHOD PARAMETER {}", methodParam);
	        log.info("Remote address {}",request.getRemoteAddr());
	        log.info("local address {}",request.getLocalAddr());
	 
	        return result;
	}
}
