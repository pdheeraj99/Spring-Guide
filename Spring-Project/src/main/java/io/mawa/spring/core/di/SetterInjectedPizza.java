package io.mawa.spring.core.di;

public class SetterInjectedPizza {

    private Sauce sauce;

    // A no-argument constructor is required for setter injection.
    public SetterInjectedPizza() {
    }

    // The dependency is injected through this setter method.
    // This is useful for optional dependencies.
    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    public void describe() {
        if (sauce != null) {
            System.out.println("This is a pizza with " + sauce.getName() + " (setter injected).");
        } else {
            System.out.println("This is a plain pizza with no sauce (setter injected).");
        }
    }
}
