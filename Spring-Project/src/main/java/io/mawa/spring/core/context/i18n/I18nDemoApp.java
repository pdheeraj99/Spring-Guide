package io.mawa.spring.core.context.i18n;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;

public class I18nDemoApp {
    public static void main(String[] args) {
        System.out.println("--- Starting the Spring Container ---");
        ApplicationContext context = new AnnotationConfigApplicationContext(I18nConfig.class);
        System.out.println("--- Container started successfully! ---");

        // The ApplicationContext itself is a MessageSource
        MessageSource messageSource = (MessageSource) context;

        System.out.println("\n--- Retrieving messages for different languages ---");

        // 1. Default (English)
        String englishGreeting = messageSource.getMessage("greeting", null, Locale.US);
        System.out.println("In English: " + englishGreeting);

        // 2. French
        String frenchGreeting = messageSource.getMessage("greeting", null, Locale.FRANCE);
        System.out.println("In French: " + frenchGreeting);

        // 3. Telugu
        Locale teluguLocale = new Locale("te", "IN");
        String teluguGreeting = messageSource.getMessage("greeting", null, teluguLocale);
        System.out.println("In Telugu: " + teluguGreeting);
    }
}
