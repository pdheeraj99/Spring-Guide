package io.mawa.spring.core.validation.conversion;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.validation.DataBinder;

public class TypeConversionDemo {

    public static void main(String[] args) {
        // --- Part 1: Custom Converter Demo ---
        System.out.println("--- 1. Demonstrating a Custom Converter ---");

        // Step 1: Create a ConversionService and add our custom converter
        // We use DefaultFormattingConversionService as it understands @NumberFormat and @DateTimeFormat
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        conversionService.addConverter(new StringToMoneyConverter());

        // Step 2: Use the service to convert a String to our Money object
        String moneyString = "1500.75 USD";
        Money myMoney = conversionService.convert(moneyString, Money.class);

        System.out.println("Converted '" + moneyString + "' to Money object: " + myMoney);
        System.out.println("Amount: " + myMoney.getAmount() + ", Currency: " + myMoney.getCurrency());


        System.out.println("\n--------------------------------------\n");


        // --- Part 2: Field Formatting Demo ---
        System.out.println("--- 2. Demonstrating Field Formatting with DataBinder ---");

        // Step 1: The DataBinder automatically uses the ConversionService.
        // We'll use the one we created, which includes our custom converter.
        MyBeanWithFormatting bean = new MyBeanWithFormatting();
        DataBinder dataBinder = new DataBinder(bean);
        dataBinder.setConversionService(conversionService);

        // Step 2: Simulate raw input with formatted values
        MutablePropertyValues pvs = new MutablePropertyValues();
        // NOTE: In a full Spring MVC app, @NumberFormat would allow us to bind "$1,234.56".
        // In this low-level demo, the default conversion service handles the direct number conversion.
        pvs.add("salary", "1234.56");
        pvs.add("joinDate", "25-12-2024"); // This uses our custom dd-MM-yyyy pattern

        // Step 3: Bind the values. The ConversionService will parse the formats.
        dataBinder.bind(pvs);

        // Step 4: Check the result
        System.out.println("Raw input: salary='1234.56', joinDate='25-12-2024'");
        System.out.println("Bound bean: " + bean);
        System.out.println("Salary class: " + bean.getSalary().getClass().getSimpleName());
        System.out.println("Join Date class: " + bean.getJoinDate().getClass().getSimpleName());
    }
}
