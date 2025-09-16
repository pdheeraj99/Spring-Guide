package io.mawa.spring.core.annotationconfig.primary;

import org.springframework.stereotype.Component;

@Component
public class Cake implements Dessert {
    @Override
    public String taste() {
        return "Soft and fluffy! 🍰";
    }
}
