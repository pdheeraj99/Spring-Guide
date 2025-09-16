package io.mawa.spring.core.annotationconfig.autowired;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "io.mawa.spring.core.annotationconfig.autowired")
public class AutowiredConfig {
    // Note that we are NOT defining a bean of type OptionalGadget here.
}
