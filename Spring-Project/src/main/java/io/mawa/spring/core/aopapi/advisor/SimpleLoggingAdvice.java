package io.mawa.spring.core.aopapi.advisor;

import org.springframework.aop.MethodBeforeAdvice;
import java.lang.reflect.Method;

public class SimpleLoggingAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("✅ [LOGGING ADVICE] - Executing before method: " + method.getName());
    }
}
