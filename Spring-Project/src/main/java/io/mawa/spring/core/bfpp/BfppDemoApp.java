package io.mawa.spring.core.bfpp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BfppDemoApp {
    public static void main(String[] args) {
        System.out.println("--- Starting the Spring Container ---");
        ApplicationContext context = new AnnotationConfigApplicationContext(BfppConfig.class);

        System.out.println("\n--- Container started. Now retrieving the bean... ---");
        DataSourceBean dataSourceBean = context.getBean("dataSource", DataSourceBean.class);

        System.out.println("\nRetrieved Bean Details: " + dataSourceBean);
        System.out.println("Notice how the password is now obfuscated!");
    }
}
