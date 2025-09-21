package io.mawa.spring.core.aopapi.proxyfactorybean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProxyFactoryBeanDemoApp {

    public static void main(String[] args) {
        // Load the Spring context from the XML file
        ApplicationContext context = new ClassPathXmlApplicationContext("pfb-context.xml");

        // Get the proxy bean from the context
        // We ask for "documentService", which is the ID of our ProxyFactoryBean
        DocumentService documentService = (DocumentService) context.getBean("documentService");

        // Use the proxy
        System.out.println("\n--- Calling readDocument() on the proxy ---");
        String contents = documentService.readDocument("MySuperSecretPlan.txt");
        System.out.println("--- Document contents: " + contents + " ---\n");
    }
}
