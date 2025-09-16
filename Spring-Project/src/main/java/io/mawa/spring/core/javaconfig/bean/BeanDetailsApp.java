package io.mawa.spring.core.javaconfig.bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDetailsApp {
    public static void main(String[] args) {
        System.out.println("--- Starting the Spring Container ---");
        // We use AnnotationConfigApplicationContext and register its close hook
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanDetailsConfig.class);
        context.registerShutdownHook();

        System.out.println("\n--- Container started. Init method should have been called. ---");


        System.out.println("\n--- Retrieving bean by its custom name 'mawaRacer' ---");
        CustomBean bean = context.getBean("mawaRacer", CustomBean.class);
        bean.doWork();

        System.out.println("\n--- Closing the container to trigger destroy method... ---");
        context.close();
    }
}
