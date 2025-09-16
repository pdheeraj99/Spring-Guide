package io.mawa.spring.core.resources.aware;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ResourceAwareDemoApp {
    public static void main(String[] args) {
        System.out.println("--- Starting the Spring Container ---");
        ApplicationContext context = new AnnotationConfigApplicationContext(ResourceAwareConfig.class);

        System.out.println("\n--- Container started. Now retrieving the bean... ---");
        MyResourceAwareBean awareBean = context.getBean(MyResourceAwareBean.class);

        System.out.println("\n--- Asking the bean to use its ResourceLoader ---");
        awareBean.showResource();

        System.out.println("\n--- Demo Complete ---");
    }
}
