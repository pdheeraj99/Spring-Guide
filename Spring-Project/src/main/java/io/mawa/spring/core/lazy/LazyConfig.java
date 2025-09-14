package io.mawa.spring.core.lazy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class LazyConfig {

    @Bean
    public EagerBean eagerBean() {
        return new EagerBean();
    }

    @Bean
    @Lazy // The @Lazy annotation can be put on the @Bean method
    public LazyBean lazyBean() {
        return new LazyBean();
    }
}
