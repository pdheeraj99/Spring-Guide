package io.mawa.spring.core.bean;

public class StaticFactoryCreatedService {
    public String getMessage() {
        return "Created via a static factory method! (Useful for legacy code)";
    }
}
