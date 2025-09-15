package io.mawa.spring.core.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LifecycleDemoApp {

    public static void main(String[] args) {
        System.out.println("--- Starting the Spring Container ---");

        // We need to use AnnotationConfigApplicationContext directly to have access to the close() method
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LifecycleConfig.class);

        System.out.println("--- Spring Container has started ---");

        // Retrieve the bean from the container
        LifecycleDemoBean demoBean = context.getBean(LifecycleDemoBean.class);

        // Use the bean
        demoBean.doWork();

        System.out.println("--- Closing the Spring Container ---");
        // Closing the context will trigger the @PreDestroy methods of singleton beans
        context.close();

        System.out.println("--- Spring Container has been closed ---");
    }
}
