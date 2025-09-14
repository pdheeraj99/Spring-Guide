package io.mawa.spring.core.scopes.singleton;

public class SingletonBean {

    public SingletonBean() {
        System.out.println("--- Creating SingletonBean ---");
        System.out.println("Hash code of created SingletonBean: " + this.hashCode());
    }

    public String getMessage() {
        return "Hello from SingletonBean! HashCode: " + this.hashCode();
    }
}
