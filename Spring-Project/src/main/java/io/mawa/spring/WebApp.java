package io.mawa.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = {
    "io.mawa.spring.core.scopes.request",
    "io.mawa.spring.core.scopes.session",
    "io.mawa.spring.core.scopes.application",
    "io.mawa.spring.realworld",
    "io.mawa.spring.core.aop" // Scanning our new AOP examples
})
public class WebApp {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(WebApp.class, args);

        System.out.println("\n--- Inspecting all bean definitions and their scopes ---");
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            // Get the BeanDefinition once by casting the context
            BeanDefinition beanDefinition = ((ConfigurableApplicationContext) context).getBeanFactory().getBeanDefinition(beanName);

            String scope = beanDefinition.getScope();
            String beanClassName = beanDefinition.getBeanClassName();

            // We only print our own beans for clarity, not all of Spring's internal beans.
            if (beanClassName != null && beanClassName.startsWith("io.mawa.spring")) {
                 System.out.println("Bean Name: " + beanName + "  |  Scope: " + (scope.isEmpty() ? "singleton" : scope));
            }
        }
        System.out.println("------------------------------------------------------\n");

        // Exit the application after the demo runs
        SpringApplication.exit(context, () -> 0);
    }

}
