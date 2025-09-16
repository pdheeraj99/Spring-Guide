package io.mawa.spring.core.factorybean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryBeanConfig {

    @Bean(name = "tool") // This bean is named "tool"
    public ToolFactory toolFactory() {
        ToolFactory factory = new ToolFactory();
        // We can configure the factory here.
        // Let's say we want a hammer.
        factory.setToolType("hammer");
        return factory;
    }
}
