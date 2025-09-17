package io.mawa.spring.core.aop.aspectj.enabling;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopEnablingApp {

    public static void main(String[] args) {
        System.out.println("--- Starting the Spring Container with AOP Enabled ---");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopEnablingConfig.class);

        System.out.println("--- Container started successfully! ---");
        System.out.println("AOP is now enabled. We can start declaring aspects.");

        context.close();
    }
}
