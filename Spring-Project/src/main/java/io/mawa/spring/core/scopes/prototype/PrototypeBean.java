package io.mawa.spring.core.scopes.prototype;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
@Scope("prototype")
public class PrototypeBean {

    public PrototypeBean() {
        System.out.println("--- Creating new PrototypeBean ---");
        System.out.println("Hash code of created PrototypeBean: " + this.hashCode() + " ✨");
    }

    @PostConstruct
    public void init() {
        System.out.println("PrototypeBean's @PostConstruct method called for hashCode: " + this.hashCode());
    }

    public void doSomething() {
        System.out.println("Doing something with prototype bean: " + this.hashCode());
    }
}
