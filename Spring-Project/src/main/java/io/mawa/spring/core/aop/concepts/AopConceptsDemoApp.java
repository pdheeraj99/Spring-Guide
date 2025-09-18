package io.mawa.spring.core.aop.concepts;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Mawa, ee App just mana AOP concepts kosam setup check cheyadaniki.
 * Ikkada manam mana PaymentService bean ni load chesi chustunnam.
 * Future lo, manam ee service calls ki mundu/tarvata magic (Advice) add cheddam.
 */
@Configuration
@ComponentScan
public class AopConceptsDemoApp {

    public static void main(String[] args) {
        System.out.println("--- Loading Spring Context for AOP Concepts ---");
        var context = new AnnotationConfigApplicationContext(AopConceptsDemoApp.class);

        // Retrieve the bean from the context
        PaymentService paymentService = context.getBean("cashPaymentService", PaymentService.class);

        System.out.println("\n--- Calling business methods (No AOP yet!) ---");
        paymentService.makePayment(150.75);
        paymentService.receivePayment(200.00);

        System.out.println("\n--- Context loaded successfully. Ready to apply Aspects! ---");
        context.close();
    }
}
