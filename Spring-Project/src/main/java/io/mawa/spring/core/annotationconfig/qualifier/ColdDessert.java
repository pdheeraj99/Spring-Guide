package io.mawa.spring.core.annotationconfig.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("cold") // Labeling this bean with the "cold" qualifier
@Primary          // Also marking it as the default choice
public class ColdDessert implements Dessert {
    @Override
    public String getName() {
        return "Cold IceCream 🍦";
    }
}
