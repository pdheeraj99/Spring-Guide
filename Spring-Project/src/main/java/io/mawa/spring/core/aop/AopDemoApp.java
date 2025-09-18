package io.mawa.spring.core.aop;

import io.mawa.spring.core.aop.aspectj.pointcuts.BusinessService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "io.mawa.spring.core.aop")
public class AopDemoApp {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(AopDemoApp.class, args);

        BusinessService businessService = context.getBean(BusinessService.class);

        System.out.println("\n--- Calling businessService.getData() ---");
        businessService.getData();

        System.out.println("\n--- Calling businessService.processData(\"Mawa\") ---");
        businessService.processData("Mawa");

        System.out.println("\n--- Calling businessService.causeException() ---");
        try {
            businessService.causeException();
        } catch (RuntimeException e) {
            System.out.println(">> Caught expected exception in main: " + e.getMessage());
        }

        System.out.println("\n--- AOP Demo Finished ---");
    }
}
