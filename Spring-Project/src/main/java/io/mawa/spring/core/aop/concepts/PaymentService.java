package io.mawa.spring.core.aop.concepts;

/**
 * Mawa, this is our "Target Object" interface.
 * Manam deeni meeda AOP magic apply cheyabothunnam.
 */
public interface PaymentService {

    void makePayment(double amount);

    void receivePayment(double amount);
}
