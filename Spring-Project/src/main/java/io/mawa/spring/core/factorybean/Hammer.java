package io.mawa.spring.core.factorybean;

public class Hammer implements Tool {
    @Override
    public String use() {
        return "Bang, bang, bang! Using the Hammer! 🔨";
    }
}
