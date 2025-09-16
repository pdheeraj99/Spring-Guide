package io.mawa.spring.core.factorybean;

public class Screwdriver implements Tool {
    @Override
    public String use() {
        return "Twist, twist, twist! Using the Screwdriver! 🪛";
    }
}
