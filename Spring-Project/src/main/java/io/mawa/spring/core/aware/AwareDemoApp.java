package io.mawa.spring.core.aware;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AwareDemoApp {
    public static void main(String[] args) {
        System.out.println("--- Starting the Spring Container ---");
        ApplicationContext context = new AnnotationConfigApplicationContext(AwareConfig.class);

        System.out.println("\n--- Retrieving the bean ---");
        MyAwareBean awareBean = context.getBean(MyAwareBean.class);

        awareBean.printAwareDetails();

        System.out.println("\n--- Spring Container Started and Demo Complete ---");
    }
}
