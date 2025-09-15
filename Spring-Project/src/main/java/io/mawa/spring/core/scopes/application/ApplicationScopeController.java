package io.mawa.spring.core.scopes.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ApplicationScopeController {

    private final ApplicationScopeBean applicationScopeBean;

    @Autowired
    public ApplicationScopeController(ApplicationScopeBean applicationScopeBean) {
        this.applicationScopeBean = applicationScopeBean;
        // Note: No proxy is needed here since a singleton's lifecycle is contained
        // within the application's lifecycle.
        System.out.println("ApplicationScopeController (Singleton) created.");
        System.out.println("Injecting REAL ApplicationScopeBean with HashCode: " + applicationScopeBean.hashCode());
    }

    @GetMapping("/application-scope")
    public Map<String, String> getApplicationScopeDetails() {
        return Map.of(
            "applicationBeanHashCode", String.valueOf(applicationScopeBean.hashCode()),
            "appStartupTime", applicationScopeBean.getStartupTime()
        );
    }
}
