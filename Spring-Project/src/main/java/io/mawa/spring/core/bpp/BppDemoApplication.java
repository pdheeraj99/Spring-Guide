package io.mawa.spring.core.bpp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BppDemoApplication {

    public static void main(String[] args) {
        System.out.println("--- Starting Spring Boot Application ---");
        ConfigurableApplicationContext context = SpringApplication.run(BppDemoApplication.class, args);

        System.out.println("\n--- Application has started. BPP should have run during startup. ---\n");

        // Retrieve the bean to use it
        MyBean myBean = context.getBean(MyBean.class);
        myBean.doSomething();

        System.out.println("\n--- Closing Spring Boot Application ---");
        context.close();
    }
}
