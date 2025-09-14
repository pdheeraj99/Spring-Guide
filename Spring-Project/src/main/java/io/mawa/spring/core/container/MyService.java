package io.mawa.spring.core.container;

import org.springframework.stereotype.Component;

@Component
public class MyService {

    public void doSomething() {
        System.out.println("Hello from MyService! The container is working! 🎉");
    }
}
