package io.mawa.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = {
    "io.mawa.spring.core.scopes.request"
})
public class WebApp {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(WebApp.class, args);

        System.out.println("\n--- Printing all bean names in the Application Context ---");
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
        System.out.println("------------------------------------------------------\n");
    }

}
