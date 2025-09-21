package io.mawa.spring.core.aopapi.pointcuts;

import org.springframework.aop.MethodBeforeAdvice;
import java.lang.reflect.Method;

public class LoggingAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("🧑‍💻 [ADVICE EXECUTED] - Logging before method: " + method.getName());
    }
}
