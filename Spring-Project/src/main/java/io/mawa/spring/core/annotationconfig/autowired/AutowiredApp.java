package io.mawa.spring.core.annotationconfig.autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutowiredApp {
    public static void main(String[] args) {
        System.out.println("--- Starting the Spring Container ---");
        ApplicationContext context = new AnnotationConfigApplicationContext(AutowiredConfig.class);
        System.out.println("--- Container started successfully! ---");

        Hero hero = context.getBean(Hero.class);
        hero.useGadget();
    }
}
