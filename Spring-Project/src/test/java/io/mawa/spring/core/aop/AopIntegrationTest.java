package io.mawa.spring.core.aop;

import io.mawa.spring.core.aop.service.Calculator;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "spring.aop.proxy-target-class=false")
public class AopIntegrationTest {

    @Autowired
    private Calculator calculator; // Autowire the interface, not the implementation

    @Autowired
    private ApplicationContext context;

    @Test
    void whenCalculatorBeanHasInterface_itShouldBeAJdkProxy() {
        System.out.println("\n--- Verifying Proxy Type ---");
        // Log the class name to see what Spring injected.
        // We now expect a JDK Dynamic Proxy, like 'com.sun.proxy.$ProxyXX'
        System.out.println("Injected Bean Class: " + calculator.getClass().getName());

        // Assert that the class is a JDK Dynamic proxy
        assertThat(calculator.getClass().getName()).contains("$Proxy");
        assertThat(AopUtils.isJdkDynamicProxy(calculator)).isTrue();

        System.out.println("\n--- Calling add() from within test ---");
        calculator.add(20, 20);
        System.out.println("--- Finished call from within test ---\n");
    }
}
