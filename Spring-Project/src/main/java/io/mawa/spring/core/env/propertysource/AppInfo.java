package io.mawa.spring.core.env.propertysource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class AppInfo {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    // We can also provide a default value in case the property is not found
    @Value("${app.author:Default Author}")
    private String appAuthor;

    @PostConstruct
    public void printAppInfo() {
        System.out.println("--- Application Information ---");
        System.out.println("Name: " + appName);
        System.out.println("Version: " + appVersion);
        System.out.println("Author: " + appAuthor);
    }
}
