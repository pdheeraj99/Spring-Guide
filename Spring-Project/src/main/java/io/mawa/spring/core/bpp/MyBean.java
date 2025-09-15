package io.mawa.spring.core.bpp;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

    public MyBean() {
        System.out.println("1. MyBean: Constructor called.");
    }

    @PostConstruct
    public void init() {
        System.out.println("3. MyBean: @PostConstruct (init) method called.");
    }

    public void doSomething() {
        System.out.println("--> MyBean: Doing something...");
    }
}
