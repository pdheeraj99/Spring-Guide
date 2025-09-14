package io.mawa.spring.core.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    // 1. Instantiation with a Constructor
    @Bean
    public ConstructorInstantiatedService constructorService() {
        return new ConstructorInstantiatedService();
    }

    // 2. Instantiation with a Static Factory Method
    @Bean
    public StaticFactoryCreatedService staticFactoryService() {
        // We tell Spring to call the static method on the factory class
        return LegacyServiceFactory.createInstance();
    }

    // 3. Instantiation with an Instance Factory Method
    // First, we need a bean of the factory itself
    @Bean
    public InstanceServiceFactory instanceServiceFactory() {
        return new InstanceServiceFactory();
    }

    // Then, we define our service bean and tell Spring to use the factory bean
    // to create it. Spring automatically knows that `instanceServiceFactory()`
    // is a dependency for this bean.
    @Bean
    public InstanceFactoryCreatedService instanceFactoryService(InstanceServiceFactory instanceServiceFactory) {
        return instanceServiceFactory.createInstance();
    }
}
