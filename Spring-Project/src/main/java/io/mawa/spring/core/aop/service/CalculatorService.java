package io.mawa.spring.core.aop.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService implements Calculator {

    @Override
    public int add(int x, int y) {
        System.out.println("Executing: " + getClass().getSimpleName() + ".add()");
        return x + y;
    }

    @Override
    public int subtract(int x, int y) {
        System.out.println("Executing: " + getClass().getSimpleName() + ".subtract()");
        return x - y;
    }

    @Override
    public int multiply(int x, int y) {
        System.out.println("Executing: " + getClass().getSimpleName() + ".multiply()");
        return x * y;
    }

    @Override
    public int divide(int x, int y) {
        System.out.println("Executing: " + getClass().getSimpleName() + ".divide()");
        if (y == 0) {
            throw new IllegalArgumentException("Cannot divide by zero!");
        }
        return x / y;
    }
}
