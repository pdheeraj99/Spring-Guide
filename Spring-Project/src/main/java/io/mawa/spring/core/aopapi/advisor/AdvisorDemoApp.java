package io.mawa.spring.core.aopapi.advisor;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class AdvisorDemoApp {

    public static void main(String[] args) {
        // 1. Create the target object
        BankingService target = new BankingServiceImpl();

        // 2. Create the Advisor, combining our custom Pointcut and Advice
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(
                new DepositPointcut(),
                new SimpleLoggingAdvice()
        );

        // 3. Create the Proxy
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);

        BankingService proxy = (BankingService) proxyFactory.getProxy();

        // 4. Use the proxy
        System.out.println("--- Calling deposit() ---");
        proxy.deposit(1000.0);

        System.out.println("\n--- Calling withdraw() ---");
        proxy.withdraw(500.0);
    }
}
