package io.mawa.spring.core.aop;

import io.mawa.spring.core.aop.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration test to verify that the AOP configuration is active
 * and that the target beans are being proxied correctly.
 */
@SpringBootTest
public class AopIntegrationTest {

    @Autowired
    private CalculatorService calculator;

    /**
     * This test confirms that the {@link CalculatorService} bean injected by Spring
     * is not the original class, but an AOP proxy created by Spring.
     * <p>
     * When Spring AOP is active and an aspect targets a bean, Spring creates a proxy
     * (usually a CGLIB subclass) to wrap the original bean. This proxy is what gets
     * injected into other beans.
     * <p>
     * By asserting that the class name contains "CGLIB", we programmatically verify
     * that our AOP setup is working and that our aspect is advising the service.
     */
    @Test
    void whenCalculatorBeanIsInjected_itShouldBeAnAopProxy() {
        // Log the class name to see what Spring injected.
        // We expect something like 'io.mawa.spring.core.aop.service.CalculatorService$$SpringCGLIB$$0'
        System.out.println("Injected Bean Class: " + calculator.getClass().getName());

        // Assert that the class is a CGLIB proxy, which proves our AOP is active
        assertThat(calculator.getClass().getName()).contains("CGLIB");

        // We can also call a method to see the advice in action during the test run
        System.out.println("\n--- Calling add() from within test ---");
        calculator.add(20, 20);
        System.out.println("--- Finished call from within test ---\n");
    }
}
