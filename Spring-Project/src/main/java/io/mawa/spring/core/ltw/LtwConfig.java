package io.mawa.spring.core.ltw;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

@Configuration
@ComponentScan(basePackages = "io.mawa.spring.core.ltw")
// This annotation tells Spring to find a LoadTimeWeaver and use it.
// It requires the -javaagent:spring-instrument.jar JVM argument to work.
@EnableLoadTimeWeaving
public class LtwConfig {
}
