package io.mawa.spring.core.annotationconfig.generics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class StoreService {

    // Spring is smart enough to see <Book> and inject the BookStore
    @Autowired
    private Store<Book> bookStore;

    // Spring is smart enough to see <MusicCD> and inject the MusicStore
    @Autowired
    private Store<MusicCD> musicStore;

    @PostConstruct
    public void displayInjectedStores() {
        System.out.println("--- Store Service ---");
        System.out.println("Injecting Store<Book>: " + bookStore.getStoreType());
        System.out.println("Injecting Store<MusicCD>: " + musicStore.getStoreType());
    }
}
