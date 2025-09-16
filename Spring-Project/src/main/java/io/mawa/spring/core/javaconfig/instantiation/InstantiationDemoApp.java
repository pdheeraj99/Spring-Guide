package io.mawa.spring.core.javaconfig.instantiation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class InstantiationDemoApp {
    public static void main(String[] args) {
        System.out.println("--- DEMO 1: Using the constructor with a @Configuration class ---");
        // For this to find MyBean, AppConfig needs @ComponentScan
        // Or we need to register MyBean as well. Let's add a @ComponentScan to AppConfig.
        // (Let's assume we've added @ComponentScan to AppConfig)
        AnnotationConfigApplicationContext context1 = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println("Context 1 contains MyBean: " + context1.containsBean("myBean"));
        context1.close();


        System.out.println("\n--- DEMO 2: Using register() and refresh() ---");
        AnnotationConfigApplicationContext context2 = new AnnotationConfigApplicationContext();
        context2.register(MyBean.class); // Registering the component class directly
        context2.refresh(); // This triggers the actual container startup
        System.out.println("Context 2 contains MyBean: " + context2.containsBean("myBean"));
        context2.close();


        System.out.println("\n--- DEMO 3: Using scan() and refresh() ---");
        AnnotationConfigApplicationContext context3 = new AnnotationConfigApplicationContext();
        context3.scan("io.mawa.spring.core.javaconfig.instantiation"); // Scanning the package
        context3.refresh();
        System.out.println("Context 3 contains MyBean: " + context3.containsBean("myBean"));
        context3.close();
    }
}
