package io.mawa.spring.core.aopapi.advisor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class AdvisorDemoAppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void advisorShouldApplyAdviceToCorrectMethod() {
        // 1. Create the target object
        BankingService target = new BankingServiceImpl();

        // 2. Create the Advisor, combining our custom Pointcut and Advice
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(
                new DepositPointcut(),
                new SimpleLoggingAdvice()
        );

        // 3. Create the Proxy
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        BankingService proxy = (BankingService) proxyFactory.getProxy();

        // 4. Use the proxy
        proxy.deposit(1000.0);
        proxy.withdraw(500.0);

        // 5. Verify the output
        String output = outContent.toString();

        assertThat(output)
                .contains("[LOGGING ADVICE] - Executing before method: deposit")
                .contains("Depositing: 1000.0")
                .contains("Withdrawing: 500.0")
                .doesNotContain("[LOGGING ADVICE] - Executing before method: withdraw");
    }
}
