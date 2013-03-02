package com.clarionmedia.infinitumdemo.aspect;

import com.clarionmedia.infinitum.aop.ProceedingJoinPoint;
import com.clarionmedia.infinitum.aop.annotation.Around;
import com.clarionmedia.infinitum.aop.annotation.Aspect;
import com.clarionmedia.infinitum.logging.Logger;

@Aspect
public class LoggingAspect {
	
	private Logger mLogger;
	
	public LoggingAspect() {
		mLogger = Logger.getInstance(getClass().getSimpleName());
	}
	
	@Around(within = "com.clarionmedia.infinitumdemo.util")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Exception {
		mLogger.debug("Entering " + joinPoint.getMethod().getName());
	    Object result = joinPoint.proceed();
	    mLogger.debug("Exiting " + joinPoint.getMethod().getName());
	    return result;
	}

}
