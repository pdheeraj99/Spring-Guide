package io.mawa.spring.core.ltw;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LtwApp {
    public static void main(String[] args) {
        System.out.println("--- Starting the Spring Container with LTW enabled ---");
        ApplicationContext context = new AnnotationConfigApplicationContext(LtwConfig.class);
        System.out.println("--- Container started successfully! ---");

        System.out.println("\n--- Getting the MyEntity bean and calling doWork() ---");
        MyEntity entity = context.getBean(MyEntity.class);
        entity.doWork();

        System.out.println("\n--- Demo Complete ---");
    }
}
