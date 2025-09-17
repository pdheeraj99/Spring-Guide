package io.mawa.spring.core.aop.proxies;

import org.springframework.stereotype.Component;

@Component("upiPaymentService")
public class UpiPaymentService implements PaymentService {
    @Override
    public void makePayment(double amount) {
        System.out.println("Processing UPI payment of: " + amount);
    }
}
