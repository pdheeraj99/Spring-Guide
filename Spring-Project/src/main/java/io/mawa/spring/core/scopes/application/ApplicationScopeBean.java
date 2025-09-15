package io.mawa.spring.core.scopes.application;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@ApplicationScope
public class ApplicationScopeBean {

    private String startupTime;

    @PostConstruct
    public void init() {
        this.startupTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("🏛️ ApplicationScopeBean created at: " + startupTime + " | HashCode: " + this.hashCode());
    }

    public String getStartupTime() {
        return startupTime;
    }
}
