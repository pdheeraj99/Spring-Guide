package io.mawa.spring.aop.introductions;

import org.springframework.stereotype.Service;

/**
 * A simple service bean. Note that this class does NOT implement UsageTrackable.
 * We will use an AOP Introduction to add that interface at runtime.
 */
@Service
public class ReportGeneratorService {

    public void generateReport(String reportName) {
        System.out.printf("📄 Generating report: %s%n", reportName);
        // Simulate some work
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("✅ Report generated successfully.");
    }
}
