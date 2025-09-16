package io.mawa.spring.core.jsr330;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Jsr330App {
    public static void main(String[] args) {
        System.out.println("--- Starting the Spring Container ---");
        ApplicationContext context = new AnnotationConfigApplicationContext(Jsr330Config.class);
        System.out.println("--- Container started successfully! ---");
        // The NotificationService will print its details automatically via @PostConstruct
    }
}
