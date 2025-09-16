package io.mawa.spring.core.env.profiles;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProfileDemoApp {

    public static void main(String[] args) {
        System.out.println("--- Starting the Spring Container ---");
        // We are not setting the profile here. It should be set via external configuration
        // (e.g., -Dspring.profiles.active=dev)
        ApplicationContext context = new AnnotationConfigApplicationContext(ProfileConfig.class);
        System.out.println("--- Container started successfully! ---");

        // The DataSourceService will print its details automatically via @PostConstruct
        // based on the active profile.
    }
}
