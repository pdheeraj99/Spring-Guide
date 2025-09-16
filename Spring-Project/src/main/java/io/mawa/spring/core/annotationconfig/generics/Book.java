package io.mawa.spring.core.annotationconfig.generics;

public class Book implements Item {
    @Override
    public String getName() {
        return "A good book";
    }
}
