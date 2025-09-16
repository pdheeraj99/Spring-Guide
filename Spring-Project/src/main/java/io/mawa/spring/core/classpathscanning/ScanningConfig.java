package io.mawa.spring.core.classpathscanning;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "io.mawa.spring.core.classpathscanning")
public class ScanningConfig {
}
