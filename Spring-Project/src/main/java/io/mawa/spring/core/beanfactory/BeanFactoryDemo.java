package io.mawa.spring.core.beanfactory;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class BeanFactoryDemo {
    public static void main(String[] args) {
        System.out.println("--- Manually setting up a BeanFactory ---");

        // 1. Create the factory
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        // 2. Create a reader to read our XML definitions
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        // 3. Load the bean definitions from the XML file
        System.out.println("Loading bean definitions from XML...");
        reader.loadBeanDefinitions(new ClassPathResource("beanfactory-beans.xml"));

        System.out.println("\n--- Factory setup complete. Now retrieving the bean. ---");
        // 4. Get the bean from the factory
        MyBean myBean = factory.getBean("myBean", MyBean.class);

        System.out.println(myBean.sayHello());
    }
}
