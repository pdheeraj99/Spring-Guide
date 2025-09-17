package io.mawa.spring.core.aop.concepts;

// This is a conceptual class to represent a Target Object.
// Our Aspect will "advise" the methods of this service.
public class MyService {

    public void doSomething() {
        System.out.println("MyService is doing its core business logic.");
    }
}
