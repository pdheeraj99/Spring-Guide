package io.mawa.spring.core.resources.introduction;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ResourceIntroDemo {

    public static void main(String[] args) throws Exception {
        ResourceLoader resourceLoader = new DefaultResourceLoader();

        System.out.println("--- 1. Loading a Classpath Resource ---");
        // Spring automatically understands the "classpath:" prefix
        Resource classpathResource = resourceLoader.getResource("classpath:my-resource.txt");
        printResourceDetails(classpathResource);
        readResourceContent(classpathResource);

        System.out.println("\n\n--- 2. Loading a File System Resource ---");
        // Spring automatically understands the "file:" prefix
        // We are loading the pom.xml from the project root
        Resource fileSystemResource = resourceLoader.getResource("file:pom.xml");
        printResourceDetails(fileSystemResource);

        System.out.println("\n\n--- 3. Loading a non-existent Resource ---");
        Resource nonExistentResource = resourceLoader.getResource("classpath:non-existent-file.txt");
        printResourceDetails(nonExistentResource);
    }

    private static void printResourceDetails(Resource resource) throws Exception {
        System.out.println("Resource exists?      : " + resource.exists());
        System.out.println("Resource is readable? : " + resource.isReadable());
        System.out.println("Resource is open?     : " + resource.isOpen());
        System.out.println("Resource is a file?   : " + resource.isFile());
        System.out.println("Filename              : " + resource.getFilename());
        System.out.println("Description           : " + resource.getDescription());
        if (resource.exists()) {
            System.out.println("URL                   : " + resource.getURL());
        } else {
            System.out.println("URL                   : [Does not exist]");
        }
    }

    private static void readResourceContent(Resource resource) throws Exception {
        if (resource.exists()) {
            System.out.println("\n--- Reading content ---");
            try (InputStream is = resource.getInputStream();
                 InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                 BufferedReader br = new BufferedReader(isr)) {
                br.lines().forEach(System.out::println);
            }
        }
    }
}
