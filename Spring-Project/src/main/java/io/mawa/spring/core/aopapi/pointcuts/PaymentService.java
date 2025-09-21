package io.mawa.spring.core.aopapi.pointcuts;

public interface PaymentService {
    void makePayment(double amount);
    void refundPayment(double amount);
    void checkBalance();
}
