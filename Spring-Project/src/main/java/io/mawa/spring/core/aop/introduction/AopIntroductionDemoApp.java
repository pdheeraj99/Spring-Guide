package io.mawa.spring.core.aop.introduction;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * Mawa, this is just a placeholder to set up our AOP learning structure.
 * Asalaina AOP magic next chapters lo start avutundi!
 * For now, this class just marks the beginning of our AOP journey.
 */
@Configuration
public class AopIntroductionDemoApp {

    public static void main(String[] args) {
        System.out.println("🚀 Welcome to the World of AOP, Mawa! 🚀");
        System.out.println("Manam AOP concepts nerchukovadaniki ready ga unnam.");
        System.out.println("Ee package lo, manam AOP related examples build cheddam.");

        // We are creating a context, but there are no beans to load yet.
        // This is just to show that our setup is working.
        new AnnotationConfigApplicationContext(AopIntroductionDemoApp.class);

        System.out.println("\nSetup complete. Ready for the next topic!");
    }
}
