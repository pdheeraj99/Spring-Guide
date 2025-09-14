package io.mawa.spring.core.dependson;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class DependsOnConfig {

    @Bean
    public EventLogger eventLogger() {
        return new EventLogger();
    }

    // We are telling Spring: "Hey, before you create this systemInitializer bean,
    // make sure the bean named 'eventLogger' is already created and ready."
    @Bean
    @DependsOn("eventLogger")
    public SystemInitializer systemInitializer() {
        return new SystemInitializer();
    }
}
