package com.eazybyte.springschoolproject.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Controller;

import java.time.Duration;
import java.time.Instant;

@Controller
@Slf4j
@Aspect
public class LoggerAspect {

    @Around("execution(* com.eazybyte.springschoolproject..*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(joinPoint.getSignature().toString()+"Method execution start");
        Instant start = Instant.now();
        Object returnObject = joinPoint.proceed();
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start,finish).toMillis();
        log.info("Time took to execute " + joinPoint.getSignature().getName() + " method is " + timeElapsed);
        log.info(joinPoint.getSignature().toString()+ " method execution end");
        return returnObject;

    }

    @AfterThrowing(value = "execution(* com.eazybyte.springschoolproject.*.*(..))" ,throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex){
        log.error(joinPoint.getSignature()+" An exception is happened due to: " + ex.getMessage());
    }

}
