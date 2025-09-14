package io.mawa.spring.core.lazy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LazyDemoApp {

    public static void main(String[] args) {
        System.out.println("--- Starting the Spring Container ---");

        ApplicationContext context = new AnnotationConfigApplicationContext(LazyConfig.class);

        System.out.println("\n--- Container has started. Notice only the EagerBean was created. ---\n");

        System.out.println("--- Now, let's ask for the LazyBean... ---");
        LazyBean lazyBean = context.getBean(LazyBean.class);
        System.out.println("--- LazyBean has been retrieved! ---");
    }
}
