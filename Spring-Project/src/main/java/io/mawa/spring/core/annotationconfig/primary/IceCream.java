package io.mawa.spring.core.annotationconfig.primary;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary // <-- The magic sticker! This is now the default Dessert.
public class IceCream implements Dessert {
    @Override
    public String taste() {
        return "Cool and sweet! 🍦";
    }
}
