package io.mawa.spring.core.spel.inspring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("someService")
public class SomeService {

    @Value("Mawa Developer")
    private String name;

    public String getName() {
        return name;
    }

    public String getGreeting(String user) {
        return "Hello, " + user + "! Welcome.";
    }
}
