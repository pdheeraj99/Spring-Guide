package io.mawa.spring.core.aopapi.pointcuts;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

public class PointcutDemoApp {

    public static void main(String[] args) {
        // 1. Create the target object
        PaymentService target = new PaymentServiceImpl();

        // 2. Create the Pointcut
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        // This is more specific. It matches any method name that starts with "make" or "refund".
        pointcut.setPatterns(".*make.*", ".*refund.*");

        // 3. Create the Advisor (Pointcut + Advice)
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new LoggingAdvice());

        // 4. Create the Proxy
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);

        PaymentService proxy = (PaymentService) proxyFactory.getProxy();

        // 5. Use the proxy
        System.out.println("--- Calling makePayment() ---");
        proxy.makePayment(150.75);

        System.out.println("\n--- Calling refundPayment() ---");
        proxy.refundPayment(50.00);

        System.out.println("\n--- Calling checkBalance() ---");
        proxy.checkBalance();
    }
}
