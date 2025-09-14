package io.mawa.spring.core.dependson;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DependsOnDemoApp {

    public static void main(String[] args) {
        System.out.println("Starting the Spring Container...");

        // Just creating the context will initialize all singleton beans
        new AnnotationConfigApplicationContext(DependsOnConfig.class);

        System.out.println("Spring Container has been started. Check the console for initialization order.");
    }
}
