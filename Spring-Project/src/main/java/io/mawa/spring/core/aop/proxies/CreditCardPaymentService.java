package io.mawa.spring.core.aop.proxies;

import org.springframework.stereotype.Service;

/**
 * Mawa, ee class ki interface ledu. Direct concrete class.
 * So, Spring deeniki CGLIB Proxy create chestundi.
 */
@Service
public class CreditCardPaymentService {
    public void makePayment(double amount) {
        System.out.println("Processing credit card payment of: " + amount);
    }
}
