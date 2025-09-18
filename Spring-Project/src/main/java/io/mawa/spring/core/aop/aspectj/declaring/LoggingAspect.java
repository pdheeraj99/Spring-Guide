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
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Component
public class LoggingAspect {

    public LoggingAspect() {
        System.out.println("✅ LoggingAspect Bean Created!");
    }

    // --- Pointcut Definitions ---

    /**
     * This pointcut matches the execution of the `getData()` method in BusinessService.
     * This is a very specific `execution` pointcut.
     */
    @Pointcut("execution(* io.mawa.spring.core.aop.aspectj.pointcuts.BusinessService.getData(..))")
    public void forBusinessServiceGetData() {}

    /**
     * This pointcut matches any method that is annotated with our custom @Loggable annotation.
     * This is a very powerful way to apply advice in a declarative way.
     */
    @Pointcut("@annotation(io.mawa.spring.core.aop.aspectj.pointcuts.Loggable)")
    public void forLoggableMethods() {}

    /**
     * This pointcut matches any method execution within any class in the 'pointcuts' package.
     * This is a good way to target a whole layer or feature area.
     */
    @Pointcut("within(io.mawa.spring.core.aop.aspectj.pointcuts.*)")
    public void forPointcutsPackage() {}

    /**
     * This is a combined pointcut. It matches methods that are BOTH within the pointcuts package
     * AND are annotated with @Loggable.
     * This shows how to create more complex and precise pointcuts.
     */
    @Pointcut("forPointcutsPackage() && forLoggableMethods()")
    public void forLoggableMethodsInPointcutsPackage() {}


    // NOTE: We have only defined the "WHERE" (the Pointcuts).
    // We have not yet defined the "WHAT" (the Advice). That's coming next!
    // When we run the app now, nothing will be "intercepted" yet. The purpose is to
    // ensure Spring can parse these pointcut definitions without errors.
}
