package io.mawa.spring.core.aop.aspectj.pointcuts;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A custom annotation we can use to mark methods that should be logged.
 * @Target(ElementType.METHOD) - This annotation can only be applied to methods.
 * @Retention(RetentionPolicy.RUNTIME) - This annotation will be available to the JVM at runtime,
 * which is necessary for AOP to find it via reflection.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Loggable {
    // This is a "marker" annotation. It doesn't have any attributes.
    // Its presence alone is what we will use for our pointcut.
}
