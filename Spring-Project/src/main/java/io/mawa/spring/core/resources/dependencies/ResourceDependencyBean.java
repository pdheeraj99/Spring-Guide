package io.mawa.spring.core.resources.dependencies;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;

@Component
public class ResourceDependencyBean {

    @Value("${template.path}")
    private Resource template;

    @Value("${all.xml.files.path}")
    private Resource[] xmlFiles;

    @PostConstruct
    public void showInjectedResources() throws IOException {
        System.out.println("--- Injected Single Resource ---");
        System.out.println("Template resource: " + template.getFilename());
        System.out.println("Template exists: " + template.exists());

        System.out.println("\n--- Injected Multiple Resources ---");
        System.out.println("Found " + xmlFiles.length + " XML files:");
        Arrays.stream(xmlFiles).forEach(resource ->
                System.out.println(" - " + resource.getFilename())
        );
    }
}
