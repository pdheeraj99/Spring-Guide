package io.mawa.spring.core.autowiring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutowiringDemoApp {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AutowiringConfig.class);

        System.out.println("\n--- Demonstrating Autowiring ---");

        // Get the beans from the container
        ConstructorAutowiredService service1 = context.getBean(ConstructorAutowiredService.class);
        SetterAutowiredService service2 = context.getBean(SetterAutowiredService.class);
        FieldAutowiredService service3 = context.getBean(FieldAutowiredService.class);

        // Use the beans
        service1.serveCustomer(101);
        service2.serveCustomer(202);
        service3.serveCustomer(303);
    }
}
