package io.mawa.spring.core.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(20) // Runs after SecurityAspect (@Order(10)) but before LoggingAspect (@Order(30))
public class AnalyticsAspect {

    /**
     * This advice runs before any method in the service package.
     * Its order of execution is determined by the @Order annotation.
     */
    @Before("io.mawa.spring.core.aop.LoggingAspect.forServicePackage()")
    public void gatherAnalytics() {
        System.out.println("======> 📈 @Order(20) Gathering ANALYTICS!");
    }
}
