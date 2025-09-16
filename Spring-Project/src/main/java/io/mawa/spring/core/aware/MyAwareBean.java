package io.mawa.spring.core.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MyAwareBean implements BeanNameAware, ApplicationContextAware {

    private String beanName;
    private ApplicationContext applicationContext;

    @Override
    public void setBeanName(String name) {
        System.out.println("setBeanName() called. My name in the container is: " + name);
        this.beanName = name;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext() called. I am now aware of the container.");
        this.applicationContext = applicationContext;
    }

    public void printAwareDetails() {
        System.out.println("--- My Aware Details ---");
        System.out.println("My bean name is: " + this.beanName);
        System.out.println("The ApplicationContext hashcode is: " + this.applicationContext.hashCode());
    }
}
