package io.mawa.spring.core.aopapi.advice;

import org.springframework.aop.AfterReturningAdvice;
import java.lang.reflect.Method;

public class SuccessMessageAfterAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("✅ [AFTER RETURNING ADVICE] - Method '" + method.getName() + "' executed successfully. Returned value: " + returnValue);
    }
}
