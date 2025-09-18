package io.mawa.spring.aop.introductions;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UsageTrackingAspect {

    /**
     * This is the Introduction (inter-type declaration).
     * value: A type pattern. It targets all beans of type ReportGeneratorService.
     * defaultImpl: The class that provides the implementation for the new interface.
     * The static field's type determines which interface is introduced.
     */
    @DeclareParents(
        value = "io.mawa.spring.aop.introductions.ReportGeneratorService",
        defaultImpl = DefaultUsageTracked.class
    )
    public static UsageTrackable mixin;

    /**
     * A regular advice that leverages the Introduction.
     * It targets any method execution within ReportGeneratorService.
     * Because of the Introduction, the 'this(trackable)' part of the pointcut
     * will now match, binding the proxy (which now implements UsageTrackable)
     * to the 'trackable' parameter.
     */
    @Before(
        "execution(* io.mawa.spring.aop.introductions.ReportGeneratorService.*(..)) && this(trackable)"
    )
    public void recordUsage(UsageTrackable trackable) {
        trackable.incrementUseCount();
    }
}
