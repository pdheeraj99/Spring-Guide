package io.mawa.spring.core.aop.proxies;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    /**
     * Mawa, ee pointcut mana package lo unna anni `makePayment` methods ni match chestundi.
     */
    @Pointcut("execution(* io.mawa.spring.core.aop.proxies.*.makePayment(..))")
    public void anyPaymentMethod() {}

    /**
     * Ee advice `anyPaymentMethod` pointcut match ayina prati sari run avutundi.
     */
    @Before("anyPaymentMethod()")
    public void logBeforePayment(JoinPoint joinPoint) {
        System.out.println("LOG: Method " + joinPoint.getSignature().getName() + " is about to be called.");
    }
}
