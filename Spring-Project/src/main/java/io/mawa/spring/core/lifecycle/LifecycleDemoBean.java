package io.mawa.spring.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class LifecycleDemoBean {

    public LifecycleDemoBean() {
        System.out.println("1. Constructor: The bean is being born! 👶");
    }

    @PostConstruct
    public void init() {
        System.out.println("2. @PostConstruct: The bean is ready to go! Dependencies are injected. 🚀");
        // Perfect place to initialize resources, load data, etc.
    }

    public void doWork() {
        System.out.println("3. doWork(): The bean is doing its job... 👷");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("4. @PreDestroy: The container is shutting down. Time to clean up! 🧹");
        // Perfect place to release resources, save state, etc.
    }
}
