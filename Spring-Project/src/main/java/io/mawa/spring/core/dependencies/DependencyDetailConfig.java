package io.mawa.spring.core.dependencies;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Configuration
public class DependencyDetailConfig {

    @Bean
    public AnotherService anotherService() {
        return new AnotherService();
    }

    @Bean
    public List<String> superheroGadgets() {
        return List.of("Super-Boot", "Grapple-Hook", "Smoke-Pellets");
    }

    @Bean
    public Set<String> superheroRules() {
        return Set.of("Never give up", "Protect the innocent", "Always work in the dark");
    }

    @Bean
    public Map<String, Integer> superheroContacts() {
        return Map.of(
            "Joker", 10,
            "Riddler", 7,
            "Penguin", 6
        );
    }

    @Bean
    public ComplexBean complexBean(
            AnotherService anotherService,
            List<String> superheroGadgets,
            Set<String> superheroRules,
            Map<String, Integer> superheroContacts
    ) {
        return new ComplexBean(anotherService, superheroGadgets, superheroRules, superheroContacts);
    }
}
