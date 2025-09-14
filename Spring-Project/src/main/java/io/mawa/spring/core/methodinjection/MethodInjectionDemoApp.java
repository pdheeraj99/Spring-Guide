package io.mawa.spring.core.methodinjection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MethodInjectionDemoApp {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(MethodInjectionConfig.class);

        SingletonService singletonService = context.getBean(SingletonService.class);

        System.out.println("--- Calling process() on the SingletonService the first time ---");
        singletonService.process();

        // Wait a moment to see a different timestamp
        Thread.sleep(1000);

        System.out.println("\n--- Calling process() on the SingletonService the second time ---");
        singletonService.process();

        System.out.println("\nNotice the different hashCodes and creation times for the PrototypeCommand!");
    }
}
