package io.mawa.spring.aop.introductions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class IntroductionsDemo {

    public static void main(String[] args) {
        // We only need to scan the 'introductions' package for this demo
        SpringApplication app = new SpringApplication(IntroductionsDemo.class);
        ApplicationContext context = app.run(args);

        System.out.println("--- Starting AOP Introductions Demo ---");

        ReportGeneratorService reportService = context.getBean(ReportGeneratorService.class);

        System.out.println("\n--- Calling original service method (1st time) ---");
        reportService.generateReport("Quarterly Financials");

        System.out.println("\n--- Calling original service method (2nd time) ---");
        reportService.generateReport("Annual Performance Review");

        System.out.println("\n--- Checking the introduced functionality ---");
        System.out.println("Casting the ReportGeneratorService bean to the introduced interface 'UsageTrackable'...");

        // This cast is only possible because of our @DeclareParents Introduction
        UsageTrackable trackable = (UsageTrackable) reportService;

        System.out.println("Successfully cast to UsageTrackable!");
        System.out.println("Calling the introduced methods...");
        System.out.println("Current Usage Count: " + trackable.getUseCount());

        System.out.println("\n--- AOP Introductions Demo Finished ---");
    }
}
