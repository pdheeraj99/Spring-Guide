package io.mawa.spring.aop.pointcuts.service;

import io.mawa.spring.aop.pointcuts.annotation.Loggable;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public void makePayment(String item, double amount) {
        System.out.printf("✅ Payment successful for item: %s, amount: %.2f%n", item, amount);
    }

    @Loggable
    public void processRefund(String transactionId) {
        System.out.printf("🔄 Processing refund for transaction: %s%n", transactionId);
    }

    public void checkBalance() {
        System.out.println("💰 Balance is sufficient.");
    }
}
