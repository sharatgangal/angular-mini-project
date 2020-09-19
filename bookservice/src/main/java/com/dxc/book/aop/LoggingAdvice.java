package com.dxc.book.aop;



import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author sgangal2
 *
 */
@Aspect
//we can use Spring AspectJ integration to define a class as Aspect using @Aspect annotation.
@Component
public class LoggingAdvice {
	
	
	private static final Logger log = LoggerFactory.getLogger(LoggingAdvice.class);
	
    
	// if you want only for certain class:  com.dxc.book.controller.bookController*.*.*(..) )
	/**
	 * 
	 */
	@Pointcut(value="execution(* com.dxc.book.*.*.*(..))")
	//Pointcut is expressions that are matched with join points to determine 
	//whether advice needs to be executed or not.
	public void myPointCut()
	{
		
	}
	
	/**
	 * @param pjp
	 * @return object of arguments in json format
	 */
	@Around("myPointCut()")
	//@Around advice surrounds a joinpoint such as a method invocation.
	public Object applicationLogger(ProceedingJoinPoint pjp)
	{
		//to get the which method is invoked
		ObjectMapper mapper=new ObjectMapper();
		String methodName=pjp.getSignature().getName();
		String className=pjp.getClass().getClass().toString();
		Object[] array=pjp.getArgs();
		try {
			log.info("method Invoked"+className+" "+"methodName :"+methodName+"()"+"arguments :"+mapper.writeValueAsString(array));
		} catch (JsonProcessingException e) {
			log.error("json processing exception has occured");
		}
		
		//to get the response of the methods
		Object object=null;
		try {
			//capture the response
			 object=pjp.proceed();
		} catch (Throwable e) {
			log.error("proceed() has encountered an exception");
		}
		try {
			log.info(className+" : "+methodName+"()"+"Response :"+mapper.writeValueAsString(object));
		} catch (JsonProcessingException e) {
			log.error("json processing exception has occured");
		}
		return object;
	}
}
