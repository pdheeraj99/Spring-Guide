package io.mawa.spring.core.spel.collections;

import io.mawa.spring.core.spel.api.Inventor;
import io.mawa.spring.core.spel.api.PlaceOfBirth;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SpelCollectionsDemo {

    public static void main(String[] args) {
        // --- Setup ---
        ExpressionParser parser = new SpelExpressionParser();
        Society society = new Society();
        society.addMember(new Inventor("Nikola Tesla", "Serbian", new GregorianCalendar(1856, 7, 9).getTime(), new PlaceOfBirth("Smiljan", "Austrian Empire"), "AC Motor"));
        society.addMember(new Inventor("Albert Einstein", "German", new GregorianCalendar(1879, 3, 14).getTime(), new PlaceOfBirth("Ulm", "German Empire"), "Photoelectric Effect"));
        society.addMember(new Inventor("Mihajlo Pupin", "Serbian", new GregorianCalendar(1858, 10, 9).getTime(), new PlaceOfBirth("Idvor", "Austrian Empire"), "Pupin Coil"));

        EvaluationContext context = new StandardEvaluationContext(society);

        System.out.println("--- 1. Collection Selection (.?[...]) ---");
        // Find all inventors who are Serbian
        String expression = "members.?[nationality == 'Serbian']";
        List<Inventor> serbianInventors = (List<Inventor>) parser.parseExpression(expression).getValue(context);
        System.out.println("Found " + serbianInventors.size() + " Serbian inventors:");
        serbianInventors.forEach(inv -> System.out.println(" - " + inv.getName()));

        System.out.println("\n--- 2. Collection Projection (.![...]) ---");
        // Get the names of ALL inventors
        expression = "members.![name]";
        List<String> names = (List<String>) parser.parseExpression(expression).getValue(context);
        System.out.println("All inventor names: " + names);

        System.out.println("\n--- 3. Combining Selection and Projection ---");
        // Get the names of only the Serbian inventors
        expression = "members.?[nationality == 'Serbian'].![name]";
        names = (List<String>) parser.parseExpression(expression).getValue(context);
        System.out.println("Serbian inventor names: " + names);
    }
}

// Helper class for the demo
class Society {
    private List<Inventor> members = new ArrayList<>();

    public void addMember(Inventor inventor) {
        members.add(inventor);
    }

    public List<Inventor> getMembers() {
        return members;
    }
}
