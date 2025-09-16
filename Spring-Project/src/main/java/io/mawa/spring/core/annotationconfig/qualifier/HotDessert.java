package io.mawa.spring.core.annotationconfig.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("hot") // Labeling this bean with the "hot" qualifier
public class HotDessert implements Dessert {
    @Override
    public String getName() {
        return "Hot Brownie 🍫";
    }
}
