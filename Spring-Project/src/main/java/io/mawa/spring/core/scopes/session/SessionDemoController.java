package io.mawa.spring.core.scopes.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SessionDemoController {

    private final ShoppingCartBean shoppingCart;

    @Autowired
    public SessionDemoController(ShoppingCartBean shoppingCart) {
        this.shoppingCart = shoppingCart;
        System.out.println("SessionDemoController (Singleton) created and injected with proxy for ShoppingCartBean.");
    }

    @GetMapping("/session/add")
    public Map<String, Object> addItemToCart(@RequestParam String item) {
        System.out.println("\n--- Handling /session/add endpoint ---");
        shoppingCart.addItem(item);
        return Map.of(
            "message", "Added '" + item + "' to your cart.",
            "cartInstanceHashCode", shoppingCart.hashCode()
        );
    }

    @GetMapping("/session/cart")
    public Map<String, Object> viewCart() {
        System.out.println("\n--- Handling /session/cart endpoint ---");
        List<String> items = shoppingCart.getItems();
        return Map.of(
            "itemsInCart", items,
            "itemCount", items.size(),
            "cartInstanceHashCode", shoppingCart.hashCode()
        );
    }
}
