package io.mawa.spring.core.javaconfig.basics;

public class Engine {
    public Engine() {
        System.out.println("Engine constructor called. HashCode: " + this.hashCode());
    }

    public String start() {
        return "Vroom vroom!";
    }
}
