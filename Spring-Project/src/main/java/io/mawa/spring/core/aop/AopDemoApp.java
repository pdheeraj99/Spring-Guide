package io.mawa.spring.core.aop;

import io.mawa.spring.core.aop.service.Calculator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "io.mawa.spring.core.aop")
public class AopDemoApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AopDemoApp.class, args);

        // Get the Calculator bean from the Spring container (requesting the interface)
        Calculator calculator = context.getBean(Calculator.class);

        System.out.println("\n--- Calling successful methods ---");
        calculator.add(10, 5);
        calculator.subtract(10, 5);
        calculator.multiply(10, 5);

        System.out.println("\n--- Calling method that throws an exception ---");
        try {
            calculator.divide(10, 0);
        } catch (Exception e) {
            System.out.println("--> DemoApp: Caught exception: " + e.getMessage());
        }

        System.out.println("\n--- Finished ---");

        // Close the context
        context.close();
    }
}
