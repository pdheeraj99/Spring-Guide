package io.mawa.spring.core.annotationconfig.primary;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PrimaryApp {
    public static void main(String[] args) {
        System.out.println("--- Starting the Spring Container ---");
        ApplicationContext context = new AnnotationConfigApplicationContext(PrimaryConfig.class);
        System.out.println("--- Container started successfully! ---");

        // The DessertShop needs a 'Dessert', and Spring will provide the @Primary one.
        DessertShop shop = context.getBean(DessertShop.class);
        shop.serveDessert();
    }
}
