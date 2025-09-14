package io.mawa.spring.core.scopes.prototype;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PrototypeScopeDemoApp {

    public static void main(String[] args) {
        System.out.println("--- Starting the Spring Container ---");
        ApplicationContext context = new AnnotationConfigApplicationContext(PrototypeScopeConfig.class);
        System.out.println("--- Spring Container has started ---");

        System.out.println("\n--- Retrieving bean 'prototypeBean' for the first time ---");
        PrototypeBean bean1 = context.getBean(PrototypeBean.class);
        System.out.println("Retrieved Bean 1 Hash Code: " + bean1.hashCode());

        System.out.println("\n--- Retrieving bean 'prototypeBean' for the second time ---");
        PrototypeBean bean2 = context.getBean(PrototypeBean.class);
        System.out.println("Retrieved Bean 2 Hash Code: " + bean2.hashCode());

        boolean areSame = (bean1 == bean2);
        System.out.println("\n--- Are the two beans the same instance? ---");
        System.out.println("Result: " + areSame + " " + (areSame ? "😞" : "🎉"));

        if (!areSame) {
            System.out.println("\nSuccess! A new instance was created each time as expected for prototype scope.");
        } else {
            System.out.println("\nSomething is wrong. The same instance was returned for a prototype-scoped bean.");
        }
    }
}
