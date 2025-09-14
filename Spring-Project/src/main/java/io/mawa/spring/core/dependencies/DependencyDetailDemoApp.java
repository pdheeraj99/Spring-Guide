package io.mawa.spring.core.dependencies;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DependencyDetailDemoApp {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DependencyDetailConfig.class);

        ComplexBean complexBean = context.getBean(ComplexBean.class);
        complexBean.displayInfo();
    }
}
