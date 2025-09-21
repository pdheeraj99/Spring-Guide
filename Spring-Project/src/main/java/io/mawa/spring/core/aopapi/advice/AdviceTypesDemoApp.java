package io.mawa.spring.core.aopapi.advice;

import org.springframework.aop.framework.ProxyFactory;

public class AdviceTypesDemoApp {

    public static void main(String[] args) {
        // Create the target object
        CalculatorService target = new CalculatorServiceImpl();

        // Create a ProxyFactory
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);

        // Add all our different advice types
        proxyFactory.addAdvice(new LoggingBeforeAdvice());
        proxyFactory.addAdvice(new SuccessMessageAfterAdvice());
        proxyFactory.addAdvice(new ExceptionHandlerThrowsAdvice());
        proxyFactory.addAdvice(new PerformanceTimingAroundAdvice()); // Around advice is powerful!

        // Get the proxy object
        CalculatorService proxy = (CalculatorService) proxyFactory.getProxy();

        // --- Demo 1: Successful Execution ---
        System.out.println("--- DEMO 1: Calling add(10, 20) ---");
        int result = proxy.add(10, 20);
        System.out.println("Final result from main: " + result);
        System.out.println("-------------------------------------\n");


        // --- Demo 2: Execution with Exception ---
        System.out.println("--- DEMO 2: Calling divide(10, 0) ---");
        try {
            proxy.divide(10, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception in main: " + e.getMessage());
        }
        System.out.println("-------------------------------------\n");
    }
}
