package io.mawa.spring.core.validation.databinding;

import io.mawa.spring.core.validation.customvalidator.RegistrationForm;
import io.mawa.spring.core.validation.customvalidator.RegistrationFormValidator;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

public class DataBinderDemo {

    public static void main(String[] args) {
        // Step 1: Create the target object. This is what we want to bind data to.
        RegistrationForm form = new RegistrationForm(null, null, null);

        // Step 2: Create the DataBinder instance for our target object.
        DataBinder dataBinder = new DataBinder(form);

        // Step 3: Set the validator that the DataBinder should use.
        dataBinder.setValidator(new RegistrationFormValidator());

        // Step 4: Simulate raw input data, like from a web request.
        // We use a MutablePropertyValues object for this.
        MutablePropertyValues pvs = new MutablePropertyValues();
        pvs.add("username", "mawa_dev");
        pvs.add("password", "password123");
        pvs.add("confirmPassword", "password456"); // Mismatched!

        System.out.println("--- 1. Binding and Validating with Mismatched Passwords ---");

        // Step 5: Bind the property values to the target object.
        dataBinder.bind(pvs);

        // Step 6: Invoke the validator.
        dataBinder.validate();

        // Step 7: Get the results. The BindingResult contains all the errors.
        BindingResult results = dataBinder.getBindingResult();

        // Step 8: Check and print the results.
        if (results.hasErrors()) {
            System.out.println("Result: DataBinder found errors! ❌");
            results.getAllErrors().forEach(error ->
                    System.out.println(" - " + error.getCode() + ": " + error.getDefaultMessage())
            );
        } else {
            System.out.println("Result: Form is valid! ✅");
        }

        System.out.println("\n--------------------------------------\n");

        System.out.println("--- 2. Binding and Validating with Valid Data ---");
        // Let's try again with valid data
        pvs.add("password", "secret");
        pvs.add("confirmPassword", "secret");

        // We need a new target object and DataBinder for a clean test
        form = new RegistrationForm(null, null, null);
        dataBinder = new DataBinder(form);
        dataBinder.setValidator(new RegistrationFormValidator());

        dataBinder.bind(pvs);
        dataBinder.validate();
        results = dataBinder.getBindingResult();

        if (results.hasErrors()) {
            System.out.println("Result: DataBinder found errors! ❌");
        } else {
            System.out.println("Result: Form is valid! ✅");
            System.out.println("Bound object: " + form);
        }
    }
}
