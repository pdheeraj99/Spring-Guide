package io.mawa.spring.core.aop;

import io.mawa.spring.aop.introductions.model.UsageTrackable;
import io.mawa.spring.aop.introductions.service.ReportGeneratorService;
import io.mawa.spring.core.aop.service.Calculator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

// Scan only the AOP-related packages to avoid bean conflicts from other lessons
@SpringBootApplication(scanBasePackages = {"io.mawa.spring.core.aop", "io.mawa.spring.aop.introductions"})
public class AopDemoApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AopDemoApp.class, args);

        System.out.println("--- Running Standard AOP Demo (Calculator) ---");
        runCalculatorDemo(context);

        System.out.println("\n--- Running AOP Introductions Demo (ReportGeneratorService) ---");
        runIntroductionsDemo(context);

        // Close the context
        context.close();
        System.out.println("\n--- Demo Finished ---");
    }

    private static void runCalculatorDemo(ConfigurableApplicationContext context) {
        // Get the Calculator bean from the Spring container (requesting the interface)
        Calculator calculator = context.getBean(Calculator.class);

        System.out.println("\n--- Calling successful methods ---");
        calculator.add(10, 5);
        calculator.subtract(10, 5);
        calculator.multiply(10, 5);

        System.out.println("\n--- Calling method that throws an exception ---");
        try {
            calculator.divide(10, 0);
        } catch (Exception e) {
            System.out.println("--> DemoApp: Caught exception: " + e.getMessage());
        }
    }

    private static void runIntroductionsDemo(ConfigurableApplicationContext context) {
        // Step 1: Get the ReportGeneratorService bean
        ReportGeneratorService reportService = context.getBean(ReportGeneratorService.class);
        System.out.println("Got bean: " + reportService.getClass().getName());

        // Step 2: Call its methods. The UsageTrackingAspect should intercept these calls.
        reportService.generateReport("Quarterly Financials");
        reportService.generateReport("Annual Performance Review");

        // Step 3: Cast the bean to the introduced interface (UsageTrackable)
        // This is the magic of introductions!
        try {
            UsageTrackable tracker = (UsageTrackable) reportService;
            System.out.println("Successfully cast ReportGeneratorService to UsageTrackable.");

            // Step 4: Verify the usage count from the introduced methods.
            // The @Before advice in UsageTrackingAspect should have incremented the count twice.
            System.out.println("Final usage count: " + tracker.getUseCount());
            if (tracker.getUseCount() == 2) {
                System.out.println("✅ Verification successful: Usage count is correct.");
            } else {
                System.out.println("❌ Verification failed: Usage count is not correct.");
            }
        } catch (ClassCastException e) {
            System.err.println("❌ Verification failed: Could not cast bean to UsageTrackable. Introduction AOP is not working.");
        }
    }
}
