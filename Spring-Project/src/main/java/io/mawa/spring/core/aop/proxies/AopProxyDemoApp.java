package io.mawa.spring.core.aop.proxies;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopProxyDemoApp {
    public static void main(String[] args) {
        System.out.println("--- Starting the Spring Container ---");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProxyConfig.class);
        System.out.println("--- Container started successfully! ---\n");

        // Get the bean that implements an interface
        PaymentService upiService = context.getBean("upiPaymentService", PaymentService.class);
        System.out.println("1. Bean that IMPLEMENTS an interface:");
        System.out.println("   - Bean Class: " + upiService.getClass().getName());
        System.out.println("   - Is it a JDK Proxy? " + (upiService.getClass().getName().contains("$Proxy")));
        upiService.makePayment(100.0);

        System.out.println("\n----------------------------------------\n");

        // Get the bean that does NOT implement an interface
        CreditCardPaymentService ccService = context.getBean("creditCardPaymentService", CreditCardPaymentService.class);
        System.out.println("2. Bean that does NOT implement an interface:");
        System.out.println("   - Bean Class: " + ccService.getClass().getName());
        System.out.println("   - Is it a CGLIB Proxy? " + (ccService.getClass().getName().contains("CGLIB")));
        ccService.makePayment(250.0);


        context.close();
    }
}
