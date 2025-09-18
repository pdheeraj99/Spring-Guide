package io.mawa.spring.core.aop.proxies;

/**
 * Mawa, this is the interface (the "rulebook").
 * Beans that implement this will be proxied by JDK Dynamic Proxy.
 */
public interface PaymentService {
    void makePayment(double amount);
}
