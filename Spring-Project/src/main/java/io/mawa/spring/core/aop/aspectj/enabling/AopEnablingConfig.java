package io.mawa.spring.core.aop.aspectj.enabling;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * The magic switch to enable Aspect-Oriented Programming.
 * This tells Spring to find any beans annotated with @Aspect and
 * create the necessary proxies.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class AopEnablingConfig {
    // This class is empty on purpose. Its only job is to enable AOP
    // and component scanning for this package.
}
