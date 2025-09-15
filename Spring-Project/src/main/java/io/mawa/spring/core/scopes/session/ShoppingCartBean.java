package io.mawa.spring.core.scopes.session;

import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCartBean {

    private final List<String> items = new ArrayList<>();

    @PostConstruct
    public void init() {
        System.out.println("🛒 New ShoppingCartBean created for a new user session! HashCode: " + this.hashCode());
    }

    public void addItem(String item) {
        items.add(item);
    }

    public List<String> getItems() {
        return Collections.unmodifiableList(items);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("💀 User session ended. ShoppingCartBean is being destroyed! HashCode: " + this.hashCode());
    }
}
