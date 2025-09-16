package io.mawa.spring.core.javaconfig.basics;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigApp {
    public static void main(String[] args) {
        System.out.println("--- Starting the Spring Container ---");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println("--- Container started successfully! ---");

        System.out.println("\n--- Retrieving beans ---");
        Car car = context.getBean(Car.class);
        Engine engine = context.getBean(Engine.class);

        System.out.println("\n--- Verifying Hashes ---");
        System.out.println("Engine instance from Car:      " + car.getEngine().hashCode());
        System.out.println("Engine instance from Context:  " + engine.hashCode());

        if (car.getEngine() == engine) {
            System.out.println("\nSuccess! The Car has the same singleton Engine instance from the context. The @Configuration proxy worked! ✅");
        } else {
            System.out.println("\nFailure! The Car got a different Engine instance. ❌");
        }
    }
}
