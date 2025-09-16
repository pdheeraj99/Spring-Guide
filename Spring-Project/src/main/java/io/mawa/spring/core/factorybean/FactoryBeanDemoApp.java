package io.mawa.spring.core.factorybean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FactoryBeanDemoApp {
    public static void main(String[] args) {
        System.out.println("--- Starting the Spring Container ---");
        ApplicationContext context = new AnnotationConfigApplicationContext(FactoryBeanConfig.class);

        System.out.println("\n--- 1. Asking for the 'tool' bean (The Product) ---");
        Tool myTool = context.getBean("tool", Tool.class);
        System.out.println("Received product: " + myTool.use());

        System.out.println("\n--- 2. Asking for the '&tool' bean (The Factory Itself) ---");
        Object factoryObject = context.getBean("&tool");
        System.out.println("Received factory object of type: " + factoryObject.getClass().getSimpleName());

        if (factoryObject instanceof ToolFactory) {
            System.out.println("Successfully retrieved the ToolFactory itself!");
        }
    }
}
