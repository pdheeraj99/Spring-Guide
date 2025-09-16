package io.mawa.spring.core.resources.implementations;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ResourceImplementationsDemo {

    public static void main(String[] args) throws Exception {
        System.out.println("--- 1. Directly creating a ClassPathResource ---");
        Resource classpathResource = new ClassPathResource("my-resource.txt");
        printResourceDetails(classpathResource);
        readResourceContent(classpathResource);

        System.out.println("\n\n--- 2. Directly creating a FileSystemResource ---");
        // For a robust example, let's create a temporary file
        java.io.File tempFile = java.io.File.createTempFile("filesystem-resource-demo", ".txt");
        tempFile.deleteOnExit(); // Clean up after the JVM exits

        // Write some content to our temporary file
        try (java.io.PrintWriter writer = new java.io.PrintWriter(tempFile)) {
            writer.println("Hello from a temporary file system resource!");
        }

        Resource fileSystemResource = new FileSystemResource(tempFile);
        printResourceDetails(fileSystemResource);
        readResourceContent(fileSystemResource);
    }

    private static void printResourceDetails(Resource resource) throws Exception {
        System.out.println("Implementation Class  : " + resource.getClass().getSimpleName());
        System.out.println("Resource exists?      : " + resource.exists());
        System.out.println("Description           : " + resource.getDescription());
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
