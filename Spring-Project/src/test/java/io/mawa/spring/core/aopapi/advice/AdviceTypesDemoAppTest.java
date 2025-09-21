package io.mawa.spring.core.aopapi.advice;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AdviceTypesDemoAppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private CalculatorService proxy;

    @BeforeEach
    public void setUp() {
        // Redirect System.out and System.err to capture console output
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        // Create the target and proxy
        CalculatorService target = new CalculatorServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);

        // Add all advice types
        proxyFactory.addAdvice(new LoggingBeforeAdvice());
        proxyFactory.addAdvice(new SuccessMessageAfterAdvice());
        proxyFactory.addAdvice(new ExceptionHandlerThrowsAdvice());
        proxyFactory.addAdvice(new PerformanceTimingAroundAdvice());

        proxy = (CalculatorService) proxyFactory.getProxy();
    }

    @AfterEach
    public void restoreStreams() {
        // Restore System.out and System.err
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void testAdviceOnSuccessfulExecution() {
        // Execute a method that should succeed
        proxy.add(10, 20);

        String output = outContent.toString();

        // Verify Before Advice
        assertThat(output).contains("[BEFORE ADVICE] - Method 'add' is about to be executed.");
        // Verify Around Advice (before proceed)
        assertThat(output).contains("[AROUND ADVICE] - Starting timer before method execution...");
        // Verify Target Method Execution
        assertThat(output).contains("Executing: 10 + 20");
        // Verify Around Advice (after proceed)
        assertThat(output).contains("[AROUND ADVICE] - Method executed successfully.");
        assertThat(output).contains("[AROUND ADVICE] - Method 'add' took");
        // Verify After Returning Advice
        assertThat(output).contains("[AFTER RETURNING ADVICE] - Method 'add' executed successfully. Returned value: 30");

        // Verify Throws Advice did NOT run
        String errOutput = errContent.toString();
        assertThat(errOutput).doesNotContain("[THROWS ADVICE]");
    }

    @Test
    void testAdviceOnExceptionalExecution() {
        // Execute a method that should throw an exception
        assertThrows(IllegalArgumentException.class, () -> {
            proxy.divide(10, 0);
        });

        String output = outContent.toString();
        String errOutput = errContent.toString();

        // Verify Around Advice (before proceed) ran
        assertThat(output).contains("[AROUND ADVICE] - Starting timer before method execution...");
        // Verify Target Method did NOT complete successfully
        assertThat(output).doesNotContain("Executing: 10 / 0");
        // Verify After Returning Advice did NOT run
        assertThat(output).doesNotContain("[AFTER RETURNING ADVICE]");

        // Verify Throws Advice ran
        assertThat(errOutput).contains("[THROWS ADVICE] - Caught an illegal argument! Details: Cannot divide by zero!");
    }
}
