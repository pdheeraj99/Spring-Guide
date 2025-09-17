package io.mawa.spring.core.aop.proxies;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy // This is the magic switch to turn on AOP proxying!
public class ProxyConfig {

    // We need an Aspect to trigger proxy creation.
    // Don't worry about the details yet, we will learn this in the next section!
    @Aspect
    @Component
    public static class DummyAspect {
        @Before("execution(* io.mawa.spring.core.aop.proxies.*.*(..))")
        public void beforeAdvice() {
            // This is a dummy advice that does nothing.
            // Its presence is enough to make Spring create proxies.
        }
    }
}
