package io.mawa.spring.core.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DIConfig {

    // Define the dependency beans
    @Bean
    public Topping cheeseTopping() {
        return new CheeseTopping();
    }

    @Bean
    public Sauce tomatoSauce() {
        return new TomatoSauce();
    }

    // Define the bean that uses constructor injection.
    // Spring sees that it needs a Topping and automatically provides the cheeseTopping bean.
    @Bean
    public ConstructorInjectedPizza constructorInjectedPizza(Topping topping) {
        return new ConstructorInjectedPizza(topping);
    }

    // Define the bean that uses setter injection.
    // Spring creates the object and then calls the `setSauce` method.
    @Bean
    public SetterInjectedPizza setterInjectedPizza() {
        SetterInjectedPizza pizza = new SetterInjectedPizza();
        pizza.setSauce(tomatoSauce());
        return pizza;
    }
}
