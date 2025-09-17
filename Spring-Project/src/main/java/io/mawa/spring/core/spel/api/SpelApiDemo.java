package io.mawa.spring.core.spel.api;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Date;
import java.util.GregorianCalendar;

public class SpelApiDemo {

    public static void main(String[] args) {
        // --- Simple Literal Expression ---
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello Mawa!'");
        String message = (String) exp.getValue();
        System.out.println("1. Literal Expression: " + message);

        // --- Method Invocation ---
        exp = parser.parseExpression("'Hello Mawa!'.concat(' How are you?')");
        message = (String) exp.getValue();
        System.out.println("2. Method Invocation: " + message);

        // --- Property Access ---
        exp = parser.parseExpression("'Hello Mawa!'.bytes.length");
        int length = (Integer) exp.getValue();
        System.out.println("3. Property Access: " + length);

        System.out.println("\n--------------------------------------\n");
        System.out.println("--- Evaluation against an Object ---");

        // Create an object to evaluate against
        Inventor tesla = new Inventor("Nikola Tesla", "Serbian", new GregorianCalendar(1856, 7, 9).getTime(), null, new String[0]);

        // Parse expression to get the name property from the object
        exp = parser.parseExpression("name");
        String name = (String) exp.getValue(tesla);
        System.out.println("4. Get property from object: " + name);

        // Use a boolean operator
        exp = parser.parseExpression("name == 'Nikola Tesla'");
        boolean result = exp.getValue(tesla, Boolean.class); // Generic getValue
        System.out.println("5. Boolean operator: " + result);

        System.out.println("\n--------------------------------------\n");
        System.out.println("--- Using an EvaluationContext ---");

        // Create an EvaluationContext to hold the root object
        EvaluationContext context = new StandardEvaluationContext(tesla);

        // Now we evaluate against the context instead of the object directly
        name = (String) parser.parseExpression("name").getValue(context);
        int year = (Integer) parser.parseExpression("birthdate.year + 1900").getValue(context);

        System.out.println("6. Get name from context: " + name);
        System.out.println("7. Get birth year from context: " + year);
    }
}
