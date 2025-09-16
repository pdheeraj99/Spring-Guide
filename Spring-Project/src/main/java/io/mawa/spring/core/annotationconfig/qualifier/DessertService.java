package io.mawa.spring.core.annotationconfig.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class DessertService {

    // Scenario 1: No qualifier. Spring will look for a @Primary bean.
    @Autowired
    private Dessert primaryDessert;

    // Scenario 2: Specifically asking for the "hot" dessert.
    @Autowired
    @Qualifier("hot")
    private Dessert hotDessert;

    // Scenario 3: Specifically asking for the "cold" dessert.
    @Autowired
    @Qualifier("cold")
    private Dessert coldDessert;

    @PostConstruct
    public void displayDesserts() {
        System.out.println("--- Dessert Service ---");
        System.out.println("Default Dessert (using @Primary): " + primaryDessert.getName());
        System.out.println("Requested Hot Dessert (using @Qualifier('hot')): " + hotDessert.getName());
        System.out.println("Requested Cold Dessert (using @Qualifier('cold')): " + coldDessert.getName());
    }
}
