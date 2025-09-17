package io.mawa.spring.core.aop.aspectj.declaring;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * This is our first Aspect.
 * 1. @Aspect tells Spring that this class is special and contains AOP instructions.
 * 2. @Component makes this class a Spring bean, so the container can manage it.
 *
 * For now, it's empty. We will add Advice and Pointcuts in the next lessons.
 */
@Aspect
@Component
public class LoggingAspect {

    public LoggingAspect() {
        System.out.println("LoggingAspect (our 'wrapping paper' design) has been created by Spring! 🎁");
    }
}
