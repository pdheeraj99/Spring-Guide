package io.mawa.spring.core.validation.customvalidator;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

public class CustomValidatorDemo {

    public static void main(String[] args) {
        // Step 1: Create the object to be validated
        RegistrationForm form = new RegistrationForm("mawa_dev", "password123", "password456");
        System.out.println("--- 1. Testing a form with MISMATCHED passwords ---");
        System.out.println("Validating form for user: " + form.getUsername());

        // Step 2: Create an Errors object to hold the validation results.
        // BeanPropertyBindingResult is a convenient implementation from Spring.
        Errors errors = new BeanPropertyBindingResult(form, "registrationForm");

        // Step 3: Create and invoke our custom validator
        RegistrationFormValidator validator = new RegistrationFormValidator();
        validator.validate(form, errors);

        // Step 4: Check for errors
        if (errors.hasErrors()) {
            System.out.println("Result: Form has errors! ❌");
            System.out.println("Found " + errors.getErrorCount() + " error(s):");
            errors.getAllErrors().forEach(error ->
                    System.out.println(" - " + error.getCode() + ": " + error.getDefaultMessage())
            );
        } else {
            System.out.println("Result: Form is valid! ✅");
        }

        System.out.println("\n--------------------------------------\n");

        System.out.println("--- 2. Testing a VALID form ---");
        form = new RegistrationForm("mawa_pro", "secret", "secret");
        System.out.println("Validating form for user: " + form.getUsername());
        errors = new BeanPropertyBindingResult(form, "registrationForm");
        validator.validate(form, errors);

        if (errors.hasErrors()) {
            System.out.println("Result: Form has errors! ❌");
        } else {
            System.out.println("Result: Form is valid! ✅");
        }
    }
}
