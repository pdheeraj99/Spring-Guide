package io.mawa.spring.core.aop.proxies;

import org.springframework.stereotype.Component;

@Component("creditCardPaymentService")
public class CreditCardPaymentService {
    public void makePayment(double amount) {
        System.out.println("Processing Credit Card payment of: " + amount);
    }
}
