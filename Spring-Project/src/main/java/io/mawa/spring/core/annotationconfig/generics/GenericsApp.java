package io.mawa.spring.core.annotationconfig.generics;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class GenericsApp {
    public static void main(String[] args) {
        System.out.println("--- Starting the Spring Container ---");
        ApplicationContext context = new AnnotationConfigApplicationContext(GenericsConfig.class);
        System.out.println("--- Container started successfully! ---");
        // The StoreService will print its details automatically via @PostConstruct
    }
}
