package io.mawa.spring.core.spel.syntax;

import io.mawa.spring.core.spel.api.Inventor;
import io.mawa.spring.core.spel.api.PlaceOfBirth;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Date;
import java.util.GregorianCalendar;

public class SpelSyntaxDemo {

    public static void main(String[] args) {
        // --- Setup ---
        ExpressionParser parser = new SpelExpressionParser();
        Inventor tesla = new Inventor("Nikola Tesla", "Serbian",
                new GregorianCalendar(1856, 7, 9).getTime(),
                new PlaceOfBirth("Smiljan", "Austrian Empire"),
                "AC Motor", "Radio", "Tesla Coil");
        EvaluationContext context = new StandardEvaluationContext(tesla);

        System.out.println("--- 1. Accessing Properties (Simple and Nested) ---");
        String name = parser.parseExpression("name").getValue(context, String.class);
        System.out.println("Name: " + name);
        String city = parser.parseExpression("placeOfBirth.city").getValue(context, String.class);
        System.out.println("City: " + city);

        System.out.println("\n--- 2. Accessing Array/List Elements ---");
        String invention = parser.parseExpression("inventions[0]").getValue(context, String.class);
        System.out.println("First invention: " + invention);

        System.out.println("\n--- 3. Calling Methods ---");
        String nameInCaps = parser.parseExpression("name.toUpperCase()").getValue(context, String.class);
        System.out.println("Name in caps: " + nameInCaps);

        System.out.println("\n--- 4. Relational and Logical Operators ---");
        boolean isSerbian = parser.parseExpression("nationality == 'Serbian'").getValue(context, Boolean.class);
        System.out.println("Is he Serbian? " + isSerbian);
        boolean isSerbianAndBornInSmiljan = parser.parseExpression("nationality == 'Serbian' and placeOfBirth.city == 'Smiljan'").getValue(context, Boolean.class);
        System.out.println("Is he Serbian AND born in Smiljan? " + isSerbianAndBornInSmiljan);

        System.out.println("\n--- 5. Mathematical Operators ---");
        int yearOfBirth = parser.parseExpression("birthdate.year + 1900").getValue(context, Integer.class);
        System.out.println("Year of birth: " + yearOfBirth);
        int ageIn1900 = parser.parseExpression("1900 - (birthdate.year + 1900)").getValue(context, Integer.class);
        System.out.println("Age in 1900: " + ageIn1900);
    }
}
