package io.mawa.spring.core.bfpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;

public class ObscenityRemoverBfpp implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("🧑‍🍳 Head Chef (BFPP) is inspecting the recipes before cooking...");

        String[] beanNames = beanFactory.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            BeanDefinition definition = beanFactory.getBeanDefinition(beanName);

            // Find the recipe for our DataSourceBean
            if ("dataSource".equals(beanName)) {
                System.out.println("Found the recipe for 'dataSource'. Changing the password ingredient.");
                // Modify the property value in the definition itself
                definition.getPropertyValues().add("password", "********");
            }
        }
    }
}
