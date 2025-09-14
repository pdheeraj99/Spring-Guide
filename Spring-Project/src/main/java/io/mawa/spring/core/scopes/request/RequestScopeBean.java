package io.mawa.spring.core.scopes.request;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
@RequestScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopeBean {

    private final String userAgent;

    @Autowired
    public RequestScopeBean(HttpServletRequest request) {
        System.out.println("--- REAL RequestScopeBean CREATED! --- HashCode: " + this.hashCode() + " ✨");
        // We are pulling data FROM the HTTP request and storing it in our bean.
        this.userAgent = request.getHeader("User-Agent");
        System.out.println("Request User-Agent captured: " + this.userAgent);
    }

    public String getUserAgent() {
        return userAgent;
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
