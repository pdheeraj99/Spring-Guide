package io.mawa.spring.core.resources.aware;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class MyResourceAwareBean implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("setResourceLoader() called. The container gave me its direct number!");
        this.resourceLoader = resourceLoader;
    }

    public void showResource() {
        System.out.println("Using the injected ResourceLoader to get a resource...");
        Resource resource = resourceLoader.getResource("classpath:my-resource.txt");
        System.out.println("Found resource: " + resource.getFilename());
        System.out.println("Resource exists: " + resource.exists());
    }
}
