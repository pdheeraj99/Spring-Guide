package io.mawa.spring.core.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MyBean) {
            System.out.println("2. BeanPostProcessor (Before): Inspected '" + beanName + "' before initialization.");
        }
        return bean; // Return the original bean
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MyBean) {
            System.out.println("4. BeanPostProcessor (After): Inspected '" + beanName + "' after initialization.");
            // You could even return a proxy or a different object here!
        }
        return bean; // Return the original (or wrapped) bean
    }
}
