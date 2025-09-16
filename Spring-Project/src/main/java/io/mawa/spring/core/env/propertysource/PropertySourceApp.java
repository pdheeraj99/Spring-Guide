package io.mawa.spring.core.env.propertysource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PropertySourceApp {
    public static void main(String[] args) {
        System.out.println("--- Starting the Spring Container ---");
        ApplicationContext context = new AnnotationConfigApplicationContext(PropertySourceConfig.class);
        System.out.println("--- Container started successfully! ---");
        // The AppInfo bean will be created and its @PostConstruct method will be called,
        // printing the details from the properties file.
    }
}
