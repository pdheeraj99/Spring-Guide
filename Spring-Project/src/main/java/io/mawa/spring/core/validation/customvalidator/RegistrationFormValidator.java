package io.mawa.spring.core.validation.customvalidator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RegistrationFormValidator implements Validator {

    /**
     * This Validator validates only RegistrationForm instances.
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return RegistrationForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegistrationForm form = (RegistrationForm) target;

        // Rule: Password must not be empty
        if (form.getPassword() == null || form.getPassword().trim().isEmpty()) {
            errors.rejectValue("password", "password.empty", "Password cannot be empty.");
        }

        // Rule: Passwords must match
        if (form.getPassword() != null && !form.getPassword().equals(form.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "password.mismatch", "Passwords do not match.");
        }
    }
}
