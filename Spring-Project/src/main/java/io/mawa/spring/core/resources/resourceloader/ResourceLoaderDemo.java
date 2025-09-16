package io.mawa.spring.core.resources.resourceloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class ResourceLoaderDemo implements CommandLineRunner {

    // The ApplicationContext itself is the ResourceLoader!
    @Autowired
    private ApplicationContext context;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--- Demo: Using ApplicationContext as a ResourceLoader ---");

        Resource resource = context.getResource("classpath:my-resource.txt");

        System.out.println("Resource loaded using ApplicationContext: " + resource.getFilename());
        System.out.println("Resource exists: " + resource.exists());

        System.out.println("\n--- Reading content ---");
        try (InputStream is = resource.getInputStream();
             InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(isr)) {
            br.lines().forEach(System.out::println);
        }

        // Exit the application after the demo runs
        SpringApplication.exit(context, () -> 0);
    }
}
