package io.mawa.spring.core.resources.dependencies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResourceDependencyApp {

    public static void main(String[] args) {
        // This will start the Spring Boot application.
        // Spring will automatically create an instance of ResourceDependencyBean.
        // The @PostConstruct method in that bean will then be called.
        SpringApplication.run(ResourceDependencyApp.class, args);
    }
}
