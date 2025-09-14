package io.mawa.spring.core.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DIDemoApp {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DIConfig.class);

        System.out.println("--- Constructor Injection Demo ---");
        ConstructorInjectedPizza constructorPizza = context.getBean(ConstructorInjectedPizza.class);
        constructorPizza.describe();

        System.out.println("\n--- Setter Injection Demo ---");
        SetterInjectedPizza setterPizza = context.getBean(SetterInjectedPizza.class);
        setterPizza.describe();
    }
}
