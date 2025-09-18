package io.mawa.spring.aop.introductions.aspect;

import io.mawa.spring.aop.introductions.model.DefaultUsageTracked;
import io.mawa.spring.aop.introductions.model.UsageTrackable;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UsageTrackingAspect {

    /**
     * This is the core of the "Introduction".
     * It declares that all beans in the specified package (and its sub-packages)
     * should also implement the UsageTrackable interface.
     *
     * value: The Pointcut expression selecting the target beans. Here, it's any class in the service package.
     * defaultImpl: The default implementation to be used for the introduced interface.
     */
    @DeclareParents(value = "io.mawa.spring.aop.introductions.service.*+", defaultImpl = DefaultUsageTracked.class)
    public static UsageTrackable mixin;

    /**
     * A Pointcut that matches any method execution within a bean that now implements UsageTrackable.
     * We use `this(trackable)` to get a reference to the proxy that implements the interface.
     */
    @Pointcut("execution(* io.mawa.spring.aop.introductions.service.*.*(..)) && this(trackable)")
    public void serviceMethodExecution(UsageTrackable trackable) {}

    /**
     * A @Before advice that runs before any method matched by the Pointcut.
     * It uses the reference to the UsageTrackable proxy to increment the usage count.
     */
    @Before("serviceMethodExecution(trackable)")
    public void recordUsage(UsageTrackable trackable) {
        System.out.println("[UsageTrackingAspect]: Intercepted method call. Incrementing usage count.");
        trackable.incrementUseCount();
    }
}
