package io.mawa.spring.core.scopes.singleton;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SingletonScopeConfig {

    @Bean
    public SingletonBean singletonBean() {
        return new SingletonBean();
    }
}
