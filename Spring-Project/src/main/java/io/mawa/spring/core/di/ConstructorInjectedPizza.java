package io.mawa.spring.core.di;

public class ConstructorInjectedPizza {

    private final Topping topping;

    // The dependency is injected through the constructor.
    // This is the recommended way for mandatory dependencies.
    public ConstructorInjectedPizza(Topping topping) {
        this.topping = topping;
    }

    public void describe() {
        System.out.println("This is a pizza with " + topping.getName() + " (constructor injected).");
    }
}
