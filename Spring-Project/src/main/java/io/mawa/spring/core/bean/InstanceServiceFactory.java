package io.mawa.spring.core.bean;

// This factory class itself is a bean managed by Spring.
public class InstanceServiceFactory {

    public InstanceFactoryCreatedService createInstance() {
        System.out.println("...Calling instance factory method...");
        // Here you could have complex logic to decide which implementation to return
        return new InstanceFactoryCreatedService();
    }
}
