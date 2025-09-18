package io.mawa.spring.core.aop.proxies;

import org.springframework.stereotype.Service;

/**
 * Mawa, ee class interface ni implement chestondi.
 * So, Spring deeniki JDK Dynamic Proxy create chestundi.
 */
@Service
public class CashPaymentService implements PaymentService {
    @Override
    public void makePayment(double amount) {
        System.out.println("Processing cash payment of: " + amount);
    }
}
