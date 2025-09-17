package io.mawa.spring.core.aop.aspectj.declaring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DeclaringAspectApp {

    public static void main(String[] args) {
        System.out.println("--- Starting the Spring Container ---");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DeclaringAspectConfig.class);

        System.out.println("\n--- Container started successfully! ---");
        System.out.println("Spring has found and created our @Aspect bean.");
        System.out.println("It's now ready to be configured with Pointcuts and Advice.");

        // We can get the service bean to see that it's also created
        MyService service = context.getBean(MyService.class);
        service.doSomething();

        context.close();
    }
}
