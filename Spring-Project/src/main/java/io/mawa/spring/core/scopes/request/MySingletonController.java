package io.mawa.spring.core.scopes.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MySingletonController {

    private final RequestScopeBean requestScopeBean;

    @Autowired
    public MySingletonController(RequestScopeBean requestScopeBean) {
        this.requestScopeBean = requestScopeBean;
        System.out.println("--- MySingletonController CREATED (Singleton) ---");
        System.out.println("Controller injecting proxy for RequestScopeBean: " + requestScopeBean.getClass().getName());
    }

    @GetMapping("/request-scope")
    public Map<String, String> getRequestScopeDetails() {
        System.out.println("\n--- Handling /request-scope endpoint ---");
        Map<String, String> response = new HashMap<>();

        // We call the bean twice to prove it's the same instance within one request.
        // The proxy forwards these calls to the same REAL bean for this request.
        response.put("beanInstance", requestScopeBean.toString());
        response.put("userAgent", requestScopeBean.getUserAgent());

        System.out.println("--- Finished handling /request-scope endpoint ---");
        return response;
    }
}
