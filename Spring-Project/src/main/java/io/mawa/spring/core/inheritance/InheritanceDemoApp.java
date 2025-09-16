package io.mawa.spring.core.inheritance;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InheritanceDemoApp {
    public static void main(String[] args) {
        System.out.println("--- Loading context from XML ---");
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-inheritance.xml");

        System.out.println("\n--- Retrieving beans ---");

        ChildBean sportsCar = context.getBean("sportsCar", ChildBean.class);
        System.out.println("Sports Car Details: " + sportsCar);

        ChildBean luxuryCar = context.getBean("luxuryCar", ChildBean.class);
        System.out.println("Luxury Car Details: " + luxuryCar);

        System.out.println("\n--- Demo Complete ---");
    }
}
