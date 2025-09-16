package io.mawa.spring.core.validation.beanvalidation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

public class BeanValidationDemo {

    public static void main(String[] args) {
        // Step 1: Create a ValidatorFactory and a Validator
        // This is the standard JSR-380 way to get a validator instance.
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        System.out.println("--- 1. Testing an INVALID User ---");
        // Step 2: Create a bean with invalid data
        User invalidUser = new User("us", "not-an-email", 17);
        System.out.println("Validating user: " + invalidUser);

        // Step 3: Validate the bean
        Set<ConstraintViolation<User>> violations = validator.validate(invalidUser);

        // Step 4: Check and print the violations
        if (violations.isEmpty()) {
            System.out.println("Result: User is valid! ✅");
        } else {
            System.out.println("Result: User is INVALID! ❌");
            System.out.println("Found " + violations.size() + " violations:");
            for (ConstraintViolation<User> violation : violations) {
                System.out.println(" - " + violation.getPropertyPath() + ": " + violation.getMessage());
            }
        }

        System.out.println("\n--------------------------------------\n");

        System.out.println("--- 2. Testing a VALID User ---");
        // Step 5: Create a valid bean
        User validUser = new User("mawa_dev", "mawa@example.com", 25);
        System.out.println("Validating user: " + validUser);

        // Step 6: Validate the bean again
        violations = validator.validate(validUser);

        // Step 7: Check and print the result
        if (violations.isEmpty()) {
            System.out.println("Result: User is valid! ✅");
        } else {
            System.out.println("Result: User is INVALID! ❌");
            violations.forEach(v -> System.out.println(" - " + v.getPropertyPath() + ": " + v.getMessage()));
        }
    }
}
