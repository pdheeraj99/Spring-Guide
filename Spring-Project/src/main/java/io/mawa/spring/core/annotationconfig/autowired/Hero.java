package io.mawa.spring.core.annotationconfig.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Hero {

    // Spring will try to find a bean of type OptionalGadget.
    // If it doesn't find one, it will NOT throw an error because required=false.
    // It will just leave this field as null.
    @Autowired(required = false)
    private OptionalGadget optionalGadget;

    public void useGadget() {
        System.out.println("--- Checking for optional gadget ---");
        if (optionalGadget != null) {
            System.out.println(optionalGadget.use());
        } else {
            System.out.println("No optional gadget was found. Continuing without it. 👍");
        }
    }
}
