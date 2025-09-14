package io.mawa.spring.core.scopes.singleton;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonScopeDemoApp {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SingletonScopeConfig.class);

        System.out.println("\n--- Retrieving bean 'singletonBean' for the first time ---");
        SingletonBean bean1 = context.getBean(SingletonBean.class);
        System.out.println("Retrieved Bean Hash Code: " + bean1.hashCode());


        System.out.println("\n--- Retrieving bean 'singletonBean' for the second time ---");
        SingletonBean bean2 = context.getBean(SingletonBean.class);
        System.out.println("Retrieved Bean Hash Code: " + bean2.hashCode());

        System.out.println("\nAre both beans the same instance? " + (bean1 == bean2));
        System.out.println("Both hash codes are the same. Spring returned the SAME instance!");
    }
}
