package io.mawa.spring.core.annotationconfig.generics;

import org.springframework.stereotype.Component;

@Component
public class BookStore implements Store<Book> {
    @Override
    public String getStoreType() {
        return "This is a Book Store! 📚";
    }
}
