package io.mawa.spring.core.resources.resolver;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

public class ResolverDemo {
    public static void main(String[] args) throws IOException {
        // We can create the resolver directly. It's a powerful utility class.
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        System.out.println("--- 1. Finding all XML files in the 'config' directory and its subdirectories ---");
        Resource[] resources = resolver.getResources("classpath:config/**/*.xml");

        System.out.println("Found " + resources.length + " files:");
        for (Resource resource : resources) {
            System.out.println(" - " + resource.getDescription());
        }

        System.out.println("\n--- 2. Finding a specific file ---");
        Resource[] specificResources = resolver.getResources("classpath:config/service-a.xml");
        System.out.println("Found " + specificResources.length + " file:");
        for (Resource resource : specificResources) {
            System.out.println(" - " + resource.getDescription());
        }
    }
}
