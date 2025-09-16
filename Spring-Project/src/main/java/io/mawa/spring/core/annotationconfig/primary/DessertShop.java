package io.mawa.spring.core.annotationconfig.primary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DessertShop {

    private final Dessert dessert;

    // When Spring tries to inject a 'Dessert' here, it will find two:
    // IceCream and Cake. Without @Primary, this would fail.
    @Autowired
    public DessertShop(Dessert dessert) {
        this.dessert = dessert;
    }

    public void serveDessert() {
        System.out.println("Today's special dessert is: " + dessert.taste());
    }
}
