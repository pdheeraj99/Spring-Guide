package io.mawa.spring.realworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RealWorldController {

    private final BusinessService businessService;

    @Autowired
    public RealWorldController(BusinessService businessService) {
        this.businessService = businessService;
        System.out.println("RealWorldController (Singleton) created.");
    }

    @GetMapping("/real-world-test")
    public String handleRequest() {
        System.out.println("\n--- Handling /real-world-test endpoint ---");
        return businessService.doBusinessLogic();
    }
}
