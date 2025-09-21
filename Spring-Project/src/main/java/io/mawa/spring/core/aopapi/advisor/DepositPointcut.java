package io.mawa.spring.core.aopapi.advisor;

import org.springframework.aop.support.StaticMethodMatcherPointcut;
import java.lang.reflect.Method;

public class DepositPointcut extends StaticMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        // This pointcut matches any method with the name "deposit"
        return "deposit".equals(method.getName());
    }
}
