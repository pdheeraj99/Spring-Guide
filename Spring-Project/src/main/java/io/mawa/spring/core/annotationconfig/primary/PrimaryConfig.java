package io.mawa.spring.core.annotationconfig.primary;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "io.mawa.spring.core.annotationconfig.primary")
public class PrimaryConfig {
    // This class is now empty because @ComponentScan will find all our
    // @Component beans (IceCream, Cake, DessertShop).
    // The magic happens with the @Primary annotation on the IceCream class itself.
}
