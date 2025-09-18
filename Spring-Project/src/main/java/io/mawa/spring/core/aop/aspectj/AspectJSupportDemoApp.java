package io.mawa.spring.core.aop.aspectj;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * Mawa, this is the placeholder for our @AspectJ journey.
 * From the next topics, we will start adding real aspects, pointcuts, and advices
 * in this package.
 */
@Configuration
public class AspectJSupportDemoApp {

    public static void main(String[] args) {
        System.out.println("🚀 Ready to start writing real Aspects with @AspectJ! 🚀");

        new AnnotationConfigApplicationContext(AspectJSupportDemoApp.class);

        System.out.println("\nSetup complete for @AspectJ support. Let's enable it in the next step!");
    }
}
