package io.mawa.spring.core.annotationconfig.generics;

import org.springframework.stereotype.Component;

@Component
public class MusicStore implements Store<MusicCD> {
    @Override
    public String getStoreType() {
        return "This is a Music Store! 🎶";
    }
}
