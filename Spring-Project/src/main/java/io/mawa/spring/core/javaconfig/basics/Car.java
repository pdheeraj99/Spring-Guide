package io.mawa.spring.core.javaconfig.basics;

public class Car {
    private final Engine engine;

    public Car(Engine engine) {
        System.out.println("Car constructor called with Engine: " + engine.hashCode());
        this.engine = engine;
    }

    public void drive() {
        System.out.println("Driving the car... " + engine.start());
    }

    public Engine getEngine() {
        return engine;
    }
}
