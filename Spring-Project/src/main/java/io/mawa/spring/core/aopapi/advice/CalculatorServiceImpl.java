package io.mawa.spring.core.aopapi.advice;

public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public int add(int a, int b) {
        System.out.println("Executing: " + a + " + " + b);
        return a + b;
    }

    @Override
    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero!");
        }
        System.out.println("Executing: " + a + " / " + b);
        return a / b;
    }
}
