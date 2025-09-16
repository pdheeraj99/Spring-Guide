package io.mawa.spring.core.annotationconfig.qualifier;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class QualifierApp {
    public static void main(String[] args) {
        System.out.println("--- Starting the Spring Container ---");
        ApplicationContext context = new AnnotationConfigApplicationContext(QualifierConfig.class);
        System.out.println("--- Container started successfully! ---");
        // The DessertService will print its details automatically via @PostConstruct
    }
}
