package io.mawa.spring.core.spel.inspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpelInSpringDemoApp {

    public static void main(String[] args) {
        // We are setting a system property here just for the demo
        System.setProperty("user.language", "en");

        ApplicationContext context = SpringApplication.run(SpelInSpringDemoApp.class, args);

        // The SpelDemoBean will print its values at startup because of @PostConstruct.
        // We just need to exit the application cleanly after it's done.
        SpringApplication.exit(context, () -> 0);
    }
}
