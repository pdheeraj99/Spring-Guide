package io.mawa.spring.core.lazy;

import org.springframework.context.annotation.Lazy;

// The @Lazy annotation can be put on the component itself
@Lazy
public class LazyBean {

    public LazyBean() {
        System.out.println("LazyBean created! 😴 (You finally called me!)");
    }
}
