package io.mawa.spring.core.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * This is our first Aspect! A "spy" agent for our application.
 * <p>
 * The {@link Aspect @Aspect} annotation tells Spring that this class contains AOP advice.
 * <p>
 * The {@link Component @Component} annotation makes this Aspect a Spring bean,
 * allowing the Spring container to detect it during component scanning and
 * use it to configure AOP proxies.
 */
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(30) // Runs after SecurityAspect (@Order(10)) and AnalyticsAspect (@Order(20))
public class LoggingAspect {

    @Pointcut("execution(* io.mawa.spring.core.aop.service.*.*(..))")
    public void forServicePackage() {}

    @Before("forServicePackage()")
    public void beforeServiceMethods(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        System.out.println("✅ @Before: Calling method: " + methodName);
    }

    @AfterReturning(
        pointcut = "forServicePackage()",
        returning = "result"
    )
    public void afterReturningFromService(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().toShortString();
        System.out.println("✅ @AfterReturning: Method '" + methodName + "' returned: " + result);
    }

    @AfterThrowing(
        pointcut = "forServicePackage()",
        throwing = "exception"
    )
    public void afterExceptionInService(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().toShortString();
        System.out.println("❌ @AfterThrowing: Exception in method '" + methodName + "'. Exception is: " + exception);
    }

    @After("forServicePackage()")
    public void afterServiceMethods(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        System.out.println("✅ @After: Finished executing method: " + methodName);
    }

    @Around("forServicePackage()")
    public Object aroundServiceMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().toShortString();

        long begin = System.currentTimeMillis();
        System.out.println("🔥 @Around: Starting timer for " + methodName);

        Object result;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            System.out.println("🔥 @Around: Exception caught in " + methodName + "! Rethrowing...");
            throw e;
        }

        long end = System.currentTimeMillis();
        long duration = end - begin;
        System.out.println("🔥 @Around: Method " + methodName + " took " + duration + " ms to complete.");

        return result;
    }
}
