package io.mawa.spring.core.spel.inspring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Component
public class SpelDemoBean {

    // 1. Literal expression
    @Value("#{'Hello Mawa!'}")
    private String simpleLiteral;

    // 2. Referring to another bean ('someService') and its property ('name')
    @Value("#{someService.name}")
    private String nameFromAnotherBean;

    // 3. Calling a method on another bean
    @Value("#{someService.getGreeting('Jules')}")
    private String greeting;

    // 4. Accessing a system property (user.language)
    @Value("#{systemProperties['user.language']}")
    private String userLanguage;

    // 5. Using operators
    @Value("#{ (5 * 10) > 40 }")
    private boolean isCalculationTrue;

    // 6. Using the T() operator to access a static constant
    @Value("#{ T(java.lang.Math).PI }")
    private double pi;

    @PostConstruct
    public void printInjectedValues() {
        System.out.println("--- SpEL Injections in Action ---");
        System.out.println("1. Literal String: " + simpleLiteral);
        System.out.println("2. From another bean's property: " + nameFromAnotherBean);
        System.out.println("3. From another bean's method: " + greeting);
        System.out.println("4. From system properties: " + userLanguage);
        System.out.println("5. Result of an operation: " + isCalculationTrue);
        System.out.println("6. Static constant value: " + pi);
        System.out.println("---------------------------------");
    }
}
