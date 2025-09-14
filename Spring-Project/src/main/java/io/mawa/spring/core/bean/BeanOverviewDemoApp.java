package io.mawa.spring.core.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanOverviewDemoApp {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);

        // Get the bean created via constructor
        ConstructorInstantiatedService service1 = context.getBean(ConstructorInstantiatedService.class);
        System.out.println("1. " + service1.getMessage());

        // Get the bean created via static factory method
        StaticFactoryCreatedService service2 = context.getBean(StaticFactoryCreatedService.class);
        System.out.println("2. " + service2.getMessage());

        // Get the bean created via instance factory method
        InstanceFactoryCreatedService service3 = context.getBean(InstanceFactoryCreatedService.class);
        System.out.println("3. " + service3.getMessage());
    }
}
