package io.mawa.spring.core.bean;

// Imagine this is a legacy class from a third-party library that we cannot change.
public class LegacyServiceFactory {

    private static final StaticFactoryCreatedService instance = new StaticFactoryCreatedService();

    // The only way to get an instance is through this static method.
    public static StaticFactoryCreatedService createInstance() {
        System.out.println("...Calling static factory method...");
        return instance;
    }
}
