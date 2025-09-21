package io.mawa.spring.core.aopapi.advice;

import org.springframework.aop.ThrowsAdvice;

public class ExceptionHandlerThrowsAdvice implements ThrowsAdvice {

    // This method will be called if an IllegalArgumentException is thrown.
    public void afterThrowing(IllegalArgumentException e) throws Throwable {
        System.err.println("🚨 [THROWS ADVICE] - Caught an illegal argument! Details: " + e.getMessage());
    }
}
