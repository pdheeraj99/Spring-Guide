package io.mawa.spring.aop.pointcuts.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PointcutLoggingAspect {

    // --- Pointcut Definitions ---

    /**
     * Pointcut 1: Matches the execution of any public method in the PaymentService.
     * This is a classic 'execution' PCD.
     */
    @Pointcut("execution(public * io.mawa.spring.aop.pointcuts.service.PaymentService.*(..))")
    public void allPaymentServiceMethods() {}

    /**
     * Pointcut 2: Matches any join point within the service package.
     * This is a 'within' PCD - simpler than execution if you just care about location.
     */
    @Pointcut("within(io.mawa.spring.aop.pointcuts.service..*)")
    public void allMethodsInServicePackage() {}


    /**
     * Pointcut 3: Matches any method annotated with our custom @Loggable annotation.
     * This is an '@annotation' PCD - very useful for targeted cross-cutting.
     */
    @Pointcut("@annotation(io.mawa.spring.aop.pointcuts.annotation.Loggable)")
    public void methodsAnnotatedWithLoggable() {}

    /**
     * Pointcut 4: A combined pointcut.
     * Matches methods that are BOTH in the service package AND annotated with @Loggable.
     */
    @Pointcut("allMethodsInServicePackage() && methodsAnnotatedWithLoggable()")
    public void loggableMethodsInServicePackage() {}


    // --- Advice Definitions ---

    // Note: We'll cover Advice in detail in the next section.
    // For now, we're just using @Before to prove our pointcuts are working.

    @Before("allPaymentServiceMethods()")
    public void beforeAnyPaymentServiceMethod() {
        System.out.println("🎁 [ASPECT LOG - using 'execution'] Wrapping a PaymentService method.");
    }

    @Before("methodsAnnotatedWithLoggable()")
    public void beforeLoggableMethod() {
        System.out.println("🔖 [ASPECT LOG - using '@annotation'] This method had a @Loggable sticker!");
    }
}
