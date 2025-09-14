package io.mawa.spring.core.container;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ContainerDemoApp {

    public static void main(String[] args) {
        // 1. Instantiate the Spring Container
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // 2. Retrieve a bean from the container
        MyService myService = context.getBean(MyService.class);

        // 3. Use the bean
        myService.doSomething();
    }
}
