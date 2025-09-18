package io.mawa.spring.core.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * An example of a stateful aspect that uses the 'perthis' instantiation model.
 * A new instance of this aspect will be created for each unique bean that is advised.
 *
 * NOTE: For Spring to honor this, the aspect itself cannot be a singleton bean.
 * We remove the @Component annotation and will instantiate it via a different mechanism
 * in a config class.
 */
@Aspect("perthis(io.mawa.spring.core.aop.LoggingAspect.forServicePackage())")
public class StatefulAnalyticsAspect {

    private int callCount = 0;

    @Before("io.mawa.spring.core.aop.LoggingAspect.forServicePackage()")
    public void gatherAnalytics() {
        callCount++;
        System.out.println("======> 📊 [Stateful] Analytics! [Instance: " + this.hashCode() + ", Count: " + callCount + "]");
    }

    public int getCallCount() {
        return callCount;
    }
}
