package io.mawa.spring.core.scopes.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RequestScopeController {

    private final RequestScopeBean requestScopeBean;

    @Autowired
    public RequestScopeController(RequestScopeBean requestScopeBean) {
        this.requestScopeBean = requestScopeBean;
        System.out.println("--- RequestScopeController CREATED (Singleton) ---");
        System.out.println("Controller injecting proxy for RequestScopeBean: " + requestScopeBean.getClass().getName());
    }

    @GetMapping("/request-scope")
    public Map<String, String> getRequestScopeDetails() {
        System.out.println("\n--- Handling /request-scope endpoint ---");

        Map<String, String> response = new HashMap<>();

        System.out.println("First call to get bean details in this request...");
        response.put("firstCallTime", requestScopeBean.getCreationTime());
        response.put("firstCallHashCode", String.valueOf(requestScopeBean.hashCode()));

        System.out.println("Second call to get bean details in this request...");
        response.put("secondCallTime", requestScopeBean.getCreationTime());
        response.put("secondCallHashCode", String.valueOf(requestScopeBean.hashCode()));

        System.out.println("--- Finished handling /request-scope endpoint ---");
        return response;
    }
}
