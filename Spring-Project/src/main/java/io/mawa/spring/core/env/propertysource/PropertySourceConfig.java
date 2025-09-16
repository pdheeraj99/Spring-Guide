package io.mawa.spring.core.env.propertysource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "io.mawa.spring.core.env.propertysource")
// This is the magic line that loads our custom properties file into the Environment
@PropertySource("classpath:app-info.properties")
public class PropertySourceConfig {
}
