package io.mawa.spring.core.aop.concepts;

import org.springframework.stereotype.Service;

/**
 * Mawa, idi mana actual business logic unna class (the "Target Object").
 * Deeniki logging, security lanti extra panulu em teliyavu. Just its core job.
 */
@Service("cashPaymentService")
public class CashPaymentService implements PaymentService {

    @Override
    public void makePayment(double amount) {
        // This is the core business logic.
        // Ee method execute ayye mundu/tarvata manam extra logic add cheddam (using AOP).
        System.out.println("💰 " + amount + " Rupees Debited (Cash Payment).");
    }

    @Override
    public void receivePayment(double amount) {
        System.out.println("💸 " + amount + " Rupees Credited (Cash Payment).");
    }
}
