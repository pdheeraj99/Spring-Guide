package io.mawa.spring.core.resources.aware;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResourceAwareConfig {

    @Bean
    public MyResourceAwareBean myResourceAwareBean() {
        return new MyResourceAwareBean();
    }
}
