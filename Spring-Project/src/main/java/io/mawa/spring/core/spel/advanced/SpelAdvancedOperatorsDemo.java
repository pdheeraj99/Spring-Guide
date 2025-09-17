package io.mawa.spring.core.spel.advanced;

import io.mawa.spring.core.spel.api.Inventor;
import io.mawa.spring.core.spel.api.PlaceOfBirth;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpelAdvancedOperatorsDemo {

    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();

        // --- Setup for programmatic examples ---
        Inventor tesla = new Inventor("Nikola Tesla", "Serbian", null, null); // No birthdate or place of birth
        Inventor pupin = new Inventor(null, "Serbian", null, null); // No name

        System.out.println("--- 1. Safe Navigation Operator (?.) ---");
        // This would throw a NullPointerException without the safe navigation operator
        String city = parser.parseExpression("placeOfBirth?.city").getValue(tesla, String.class);
        System.out.println("City (should be null): " + city);

        System.out.println("\n--- 2. Elvis Operator (?:) ---");
        // Provides a default value if the property is null
        String name = parser.parseExpression("name ?: 'Unknown'").getValue(pupin, String.class);
        System.out.println("Inventor name (with default): " + name);

        System.out.println("\n--- 3. Type Operator (T(...)) ---");
        // Accessing a static constant
        Double pi = parser.parseExpression("T(java.lang.Math).PI").getValue(Double.class);
        System.out.println("Value of PI: " + pi);
        // Calling a static method
        long random = parser.parseExpression("T(java.lang.System).currentTimeMillis()").getValue(Long.class);
        System.out.println("Current time from static method: " + random);

        System.out.println("\n--------------------------------------\n");
        System.out.println("--- 4. Bean Reference (@...) inside a Spring Context ---");

        // To demonstrate @, we need a Spring context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
        MyBean myBean = context.getBean(MyBean.class);

        myBean.printValues();
        context.close();
    }
}

// --- Configuration and Beans for the Spring Context part of the demo ---

@Configuration
class DemoConfig {
    @Bean
    public MyBean myBean() {
        return new MyBean();
    }

    @Bean
    public AnotherBean anotherBean() {
        return new AnotherBean();
    }
}

class AnotherBean {
    private String value = "Value from Another Bean";

    public String getValue() {
        return value;
    }
}

class MyBean {
    @org.springframework.beans.factory.annotation.Value("#{ @anotherBean.value }")
    private String injectedValue;

    public void printValues() {
        System.out.println("Value injected from another bean using SpEL: '" + injectedValue + "'");
    }
}
