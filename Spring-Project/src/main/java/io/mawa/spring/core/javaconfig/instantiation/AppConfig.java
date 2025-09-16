package io.mawa.spring.core.javaconfig.instantiation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "io.mawa.spring.core.javaconfig.instantiation")
public class AppConfig {
    // Now this configuration will find MyBean when passed to the constructor.
}
