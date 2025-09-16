package io.mawa.spring.core.annotationconfig.resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ResourceApp {
    public static void main(String[] args) {
        System.out.println("--- Starting the Spring Container ---");
        ApplicationContext context = new AnnotationConfigApplicationContext(ResourceConfig.class);
        System.out.println("--- Container started successfully! ---");
        // The ReportService will print its details automatically via @PostConstruct
    }
}
