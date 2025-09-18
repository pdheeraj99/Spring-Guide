package io.mawa.spring.core.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(10) // Lowest number = Highest precedence. This will run first.
public class SecurityAspect {

    /**
     * This advice runs before any method in the service package.
     * Due to the @Order(10), it will execute before the advice in
     * AnalyticsAspect (@Order(20)) and LoggingAspect (@Order(30)).
     */
    @Before("io.mawa.spring.core.aop.LoggingAspect.forServicePackage()")
    public void performSecurityCheck() {
        System.out.println("======> 👮‍♂️ @Order(10) Performing SECURITY CHECK!");
    }
}
