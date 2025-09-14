package io.mawa.spring.core.dependencies;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ComplexBean {

    private final AnotherService anotherService;
    private final List<String> superheroGadgets;
    private final Set<String> superheroRules;
    private final Map<String, Integer> superheroContacts;

    public ComplexBean(
            AnotherService anotherService,
            List<String> superheroGadgets,
            Set<String> superheroRules,
            Map<String, Integer> superheroContacts) {
        this.anotherService = anotherService;
        this.superheroGadgets = superheroGadgets;
        this.superheroRules = superheroRules;
        this.superheroContacts = superheroContacts;
    }

    public void displayInfo() {
        System.out.println("--- Super Bean Assembled! 🦸‍♂️ ---");
        System.out.println("Simple Dependency: " + anotherService.getMessage());
        System.out.println("\n--- Gadget List (Utility Belt) ---");
        superheroGadgets.forEach(gadget -> System.out.println("- " + gadget));
        System.out.println("\n--- Rule Set ---");
        superheroRules.forEach(rule -> System.out.println("- " + rule));
        System.out.println("\n--- Secret Contacts (Map) ---");
        superheroContacts.forEach((name, level) -> System.out.println("- " + name + " (Threat Level: " + level + ")"));
    }
}
