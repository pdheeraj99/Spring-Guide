package io.mawa.spring.core.aopapi.pointcuts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class PointcutDemoAppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        // Redirect System.out to capture console output
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        // Restore System.out
        System.setOut(originalOut);
    }

    @Test
    void testPointcutAppliesAdviceCorrectly() {
        // 1. Create the target object
        PaymentService target = new PaymentServiceImpl();

        // 2. Create the Pointcut to match methods with "make" or "refund" in their name
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPatterns(".*make.*", ".*refund.*");

        // 3. Create the Advisor
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new LoggingAdvice());

        // 4. Create the Proxy
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        PaymentService proxy = (PaymentService) proxyFactory.getProxy();

        // 5. Use the proxy
        proxy.makePayment(100.0);
        proxy.refundPayment(50.0);
        proxy.checkBalance();

        // 6. Verify the output
        String output = outContent.toString();

        assertThat(output)
                .contains("[ADVICE EXECUTED] - Logging before method: makePayment")
                .contains("Processing payment of: 100.0")
                .contains("[ADVICE EXECUTED] - Logging before method: refundPayment")
                .contains("Processing refund of: 50.0")
                .contains("Checking account balance...")
                .doesNotContain("[ADVICE EXECUTED] - Logging before method: checkBalance");
    }
}
