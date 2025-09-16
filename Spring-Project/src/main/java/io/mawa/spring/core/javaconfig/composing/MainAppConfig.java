package io.mawa.spring.core.javaconfig.composing;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DataSourceConfig.class, ServiceConfig.class}) // The magic happens here!
public class MainAppConfig {
    // This class can be empty. Its only job is to import the other configs.
}
