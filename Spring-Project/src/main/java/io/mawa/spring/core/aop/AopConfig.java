package io.mawa.spring.core.aop;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * This configuration class enables Spring's support for Aspect-Oriented Programming (AOP)
 * using the @AspectJ style.
 *
 * @EnableAspectJAutoProxy tells Spring to find any beans annotated with @Aspect
 * and create the necessary proxies to apply their advice.
 *
 * Note: In a typical Spring Boot application with the 'spring-boot-starter-aop'
 * dependency, this auto-configuration is often handled automatically. We are defining
 * it explicitly here for clarity and learning purposes.
 */
@Configuration
@EnableAspectJAutoProxy
public class AopConfig {
}
