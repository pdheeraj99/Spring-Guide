package io.mawa.spring.core.factorybean;

import org.springframework.beans.factory.FactoryBean;

// This is our factory bean. It produces objects of type 'Tool'.
public class ToolFactory implements FactoryBean<Tool> {

    private String toolType;

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    @Override
    public Tool getObject() throws Exception {
        System.out.println("🏭 ToolFactory is producing a tool of type: " + toolType);
        if ("hammer".equalsIgnoreCase(toolType)) {
            return new Hammer();
        } else {
            return new Screwdriver();
        }
    }

    @Override
    public Class<?> getObjectType() {
        return Tool.class;
    }

    @Override
    public boolean isSingleton() {
        // We can decide if the produced tools are singletons or prototypes.
        // Let's make them prototypes for this demo.
        return false;
    }
}
