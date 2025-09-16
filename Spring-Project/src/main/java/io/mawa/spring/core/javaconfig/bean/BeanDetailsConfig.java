package io.mawa.spring.core.javaconfig.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanDetailsConfig {

    @Bean(
        name = {"mawaRacer", "myCoolBean"}, // Custom name and an alias
        initMethod = "customInit",         // Custom init method
        destroyMethod = "customDestroy"    // Custom destroy method
    )
    public CustomBean customBean() {
        return new CustomBean();
    }
}
