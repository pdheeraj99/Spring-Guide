package io.mawa.spring.core.javaconfig.basics;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // This enables "full" mode with CGLIB proxying
public class AppConfig {

    @Bean
    public Engine engine() {
        return new Engine();
    }

    @Bean
    public Car car() {
        // We are calling the engine() method directly.
        // In a @Configuration class, Spring will intercept this call
        // and ensure the singleton 'engine' bean is returned,
        // not a new instance.
        return new Car(engine());
    }
}
