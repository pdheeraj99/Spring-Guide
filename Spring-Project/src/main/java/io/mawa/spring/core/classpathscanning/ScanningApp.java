package io.mawa.spring.core.classpathscanning;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class ScanningApp {
    public static void main(String[] args) {
        System.out.println("--- Starting the Spring Container ---");
        ApplicationContext context = new AnnotationConfigApplicationContext(ScanningConfig.class);
        System.out.println("--- Container started successfully! ---");

        System.out.println("\n--- Beans found by the Detective Agency (@ComponentScan) ---");
        String[] beanNames = context.getBeanDefinitionNames();

        Arrays.stream(beanNames)
              .filter(name -> !name.startsWith("org.springframework")) // Filter out internal Spring beans
              .forEach(name -> System.out.println(" - Found bean: " + name));

        // Let's verify one of them
        MyService myService = context.getBean(MyService.class);
        System.out.println("\nVerification: " + myService.serve());
    }
}
