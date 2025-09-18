package io.mawa.spring.core.aop;

import io.mawa.spring.core.aop.service.DefaultUsageTrackable;
import io.mawa.spring.core.aop.service.UsageTrackable;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UsageTrackingAspect {

    /**
     * This is an Introduction, also known as an inter-type declaration.
     * It declares that any bean matching the pointcut expression should also implement
     * the `UsageTrackable` interface.
     *
     * @param value A pointcut expression targeting all types in the service package.
     *              The '+' indicates that subclasses are also matched.
     * @param defaultImpl The class that provides the implementation for the new interface.
     */
    @DeclareParents(
            value = "io.mawa.spring.core.aop.service.*+",
            defaultImpl = DefaultUsageTrackable.class
    )
    public static UsageTrackable mixin;

}
