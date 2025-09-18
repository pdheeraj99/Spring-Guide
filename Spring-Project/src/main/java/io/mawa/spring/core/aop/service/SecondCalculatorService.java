package io.mawa.spring.core.aop.service;

import org.springframework.stereotype.Service;

/**
 * A second implementation of the Calculator interface.
 * This is created to demonstrate that prototype-scoped aspects will create
 * a new instance for each distinct advised bean.
 */
@Service
public class SecondCalculatorService implements Calculator {

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
