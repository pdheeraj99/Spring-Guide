package io.mawa.spring.core.aop.aspectj.declaring;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * This is our first Aspect!
 *
 * @Aspect - This annotation tells Spring that this class is an Aspect. It's like putting on a "Director" badge.
 * @Component - This annotation makes this class a Spring bean, so the container can manage it and detect it
 * during component scanning.
 *
 * IMPORTANT: Both annotations are needed for auto-detection to work!
 */
@Aspect
@Component
public class LoggingAspect {

    // For now, this aspect is empty. It's like a director who has been hired but hasn't been given any scenes to shoot yet.
    // In the next sections, we will add "Advice" and "Pointcuts" here to make it do useful things!

    public LoggingAspect() {
        System.out.println("✅ LoggingAspect Bean Created in io.mawa.spring.core.aop.aspectj.declaring!");
    }
}
