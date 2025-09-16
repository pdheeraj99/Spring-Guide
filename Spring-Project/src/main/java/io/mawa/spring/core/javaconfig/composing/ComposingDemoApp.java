package io.mawa.spring.core.javaconfig.composing;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ComposingDemoApp {
    public static void main(String[] args) {
        System.out.println("--- Starting the Spring Container with ONLY MainAppConfig ---");
        ApplicationContext context = new AnnotationConfigApplicationContext(MainAppConfig.class);
        System.out.println("--- Container started successfully! ---");

        System.out.println("\n--- Retrieving MyService bean (which was defined in ServiceConfig) ---");
        MyService service = context.getBean(MyService.class);
        service.doWork();

        System.out.println("\nIt works! The @Import annotation successfully loaded beans from other configuration classes.");
    }
}
