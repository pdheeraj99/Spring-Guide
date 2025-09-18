package io.mawa.spring.core.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;

/**
 * This configuration class enables Spring's support for Aspect-Oriented Programming (AOP)
 * using the @AspectJ style.
 */
@Configuration
@EnableAspectJAutoProxy
public class AopConfig {

    /**
     * Defines the StatefulAnalyticsAspect as a bean.
     * Crucially, it is defined with "prototype" scope. This means that Spring will
     * create a new instance of this aspect every time it is needed, which allows
     * the "perthis" instantiation model defined on the aspect itself to work correctly.
     *
     * If this were a singleton, Spring would reuse the same aspect instance for all advised beans.
     * @return A new instance of StatefulAnalyticsAspect.
     */
    @Bean
    @Scope("prototype")
    public StatefulAnalyticsAspect statefulAnalyticsAspect() {
        return new StatefulAnalyticsAspect();
    }
}
