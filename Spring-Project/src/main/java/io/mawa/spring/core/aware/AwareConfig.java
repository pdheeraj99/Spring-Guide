package io.mawa.spring.core.aware;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwareConfig {

    @Bean
    public MyAwareBean myAwesomeAwareBean() {
        return new MyAwareBean();
    }
}
