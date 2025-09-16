package io.mawa.spring.core.javaconfig.bean;

public class CustomBean {

    public CustomBean() {
        System.out.println("CustomBean: Constructor called.");
    }

    public void customInit() {
        System.out.println("CustomBean: The custom init-method was called!");
    }

    public void customDestroy() {
        System.out.println("CustomBean: The custom destroy-method was called!");
    }

    public void doWork() {
        System.out.println("CustomBean: Doing my work...");
    }
}
