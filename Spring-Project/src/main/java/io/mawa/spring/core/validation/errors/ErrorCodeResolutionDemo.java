package io.mawa.spring.core.validation.errors;

import io.mawa.spring.core.validation.customvalidator.RegistrationForm;
import io.mawa.spring.core.validation.customvalidator.RegistrationFormValidator;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.Locale;

public class ErrorCodeResolutionDemo {

    public static void main(String[] args) {
        // Step 1: Set up a MessageSource
        // This is our "secret decoder ring" for error messages.
        MessageSource messageSource = createMessageSource();

        // Step 2: Create the target object and DataBinder, just like before
        RegistrationForm form = new RegistrationForm("mawa_dev", "password123", "password456");
        DataBinder dataBinder = new DataBinder(form);
        dataBinder.setValidator(new RegistrationFormValidator());

        // Step 3: Bind and validate
        MutablePropertyValues pvs = new MutablePropertyValues();
        pvs.add("password", "password123");
        pvs.add("confirmPassword", "password456"); // Mismatched!
        dataBinder.bind(pvs);
        dataBinder.validate();

        // Step 4: Get the results
        BindingResult results = dataBinder.getBindingResult();

        System.out.println("--- Translating Error Codes to Messages ---");

        // Step 5: Check for errors and use the MessageSource to translate them
        if (results.hasErrors()) {
            System.out.println("Found " + results.getErrorCount() + " error(s):");
            results.getAllErrors().forEach(error -> {
                // Here's the magic! We use the messageSource to get the friendly message.
                String message = messageSource.getMessage(error, Locale.US);
                System.out.println(" - " + message);
            });
        } else {
            System.out.println("No errors found!");
        }
    }

    private static MessageSource createMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        // The basename "messages" tells it to look for "messages.properties", "messages_te.properties", etc.
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
