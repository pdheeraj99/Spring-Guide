package io.mawa.spring.core.annotationconfig.generics;

public interface Store<T extends Item> {
    String getStoreType();
}
