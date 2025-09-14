package io.mawa.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// We explicitly scan only the packages we need for the web examples
// to avoid conflicts with older, standalone examples.
@SpringBootApplication
@ComponentScan(basePackages = {
    "io.mawa.spring.core.scopes.request"
})
public class WebApp {

    public static void main(String[] args) {
        SpringApplication.run(WebApp.class, args);
    }

}
