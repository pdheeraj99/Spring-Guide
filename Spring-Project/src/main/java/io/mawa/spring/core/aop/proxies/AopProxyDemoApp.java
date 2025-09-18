package io.mawa.spring.core.aop.proxies;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy // Mawa, idi chala important! AOP enable cheyadaniki ee annotation kavali.
public class AopProxyDemoApp {

    public static void main(String[] args) {
        System.out.println("--- Loading Spring Context to demonstrate AOP Proxies ---");
        var context = new AnnotationConfigApplicationContext(AopProxyDemoApp.class);

        // Get the beans from the container
        PaymentService cashPaymentService = context.getBean(PaymentService.class);
        CreditCardPaymentService creditCardPaymentService = context.getBean(CreditCardPaymentService.class);

        System.out.println("\n--- Inspecting the Proxy Classes ---");

        // JDK Dynamic Proxy (implements the interface)
        System.out.println("CashPaymentService Bean is of type: " + cashPaymentService.getClass().getName());
        // You will see something like: com.sun.proxy.$Proxy...

        // CGLIB Proxy (subclasses the original class)
        System.out.println("CreditCardPaymentService Bean is of type: " + creditCardPaymentService.getClass().getName());
        // You will see something like: io.mawa.spring.core.aop.proxies.CreditCardPaymentService$$SpringCGLIB$$...

        System.out.println("\n--- Calling methods to trigger the advice ---");
        cashPaymentService.makePayment(100.0);
        creditCardPaymentService.makePayment(250.0);

        context.close();
    }
}
