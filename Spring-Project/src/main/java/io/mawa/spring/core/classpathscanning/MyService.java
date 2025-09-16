package io.mawa.spring.core.classpathscanning;

import org.springframework.stereotype.Service;

@Service // A specialization of @Component for the service layer
public class MyService {
    public String serve() {
        return "Serving you from MyService!";
    }
}
