package io.mawa.spring.aop.pointcuts;

import io.mawa.spring.aop.pointcuts.service.PaymentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy // This is crucial to enable @AspectJ support
@ComponentScan(basePackages = "io.mawa.spring.aop.pointcuts")
public class PointcutDemo {

    public static void main(String[] args) {
        System.out.println("--- Starting Pointcut Demo ---");
        ApplicationContext context = SpringApplication.run(PointcutDemo.class, args);

        // Get the PaymentService bean from the container
        PaymentService paymentService = context.getBean(PaymentService.class);

        System.out.println("\n--- Calling makePayment() ---");
        System.out.println("EXPECTED: Advice from 'execution' pointcut should trigger.");
        paymentService.makePayment("Laptop", 1200.00);

        System.out.println("\n--- Calling processRefund() ---");
        System.out.println("EXPECTED: Advice from 'execution' AND '@annotation' pointcuts should trigger.");
        paymentService.processRefund("TXN-12345");

        System.out.println("\n--- Calling checkBalance() ---");
        System.out.println("EXPECTED: Advice from 'execution' pointcut should trigger.");
        paymentService.checkBalance();

        System.out.println("\n--- Pointcut Demo Finished ---");
    }
}
