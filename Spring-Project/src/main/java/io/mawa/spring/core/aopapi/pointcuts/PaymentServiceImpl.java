package io.mawa.spring.core.aopapi.pointcuts;

public class PaymentServiceImpl implements PaymentService {

    @Override
    public void makePayment(double amount) {
        System.out.println("Processing payment of: " + amount);
    }

    @Override
    public void refundPayment(double amount) {
        System.out.println("Processing refund of: " + amount);
    }

    @Override
    public void checkBalance() {
        System.out.println("Checking account balance...");
    }
}
