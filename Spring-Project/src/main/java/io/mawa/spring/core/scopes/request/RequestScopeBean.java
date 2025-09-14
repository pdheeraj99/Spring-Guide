package io.mawa.spring.core.scopes.request;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.time.LocalDateTime;

@Component
@RequestScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopeBean {

    private final String creationTime = LocalDateTime.now().toString();

    public RequestScopeBean() {
        System.out.println("--- RequestScopeBean CREATED! --- Time: " + creationTime + ", HashCode: " + this.hashCode() + " ✨");
    }

    public String getCreationTime() {
        return creationTime;
    }

    @PostConstruct
    public void init() {
        System.out.println("RequestScopeBean @PostConstruct: Bean is ready for request. HashCode: " + this.hashCode());
    }

    @PreDestroy
    public void destroy() {
        System.out.println("RequestScopeBean @PreDestroy: Request is complete, bean is being destroyed. HashCode: " + this.hashCode() + " 💀");
    }
}
