package io.mawa.spring.core.classpathscanning;

import org.springframework.stereotype.Component;

@Component // The most generic stereotype annotation
public class MyComponent {
    public String greet() {
        return "Hello from MyComponent!";
    }
}
