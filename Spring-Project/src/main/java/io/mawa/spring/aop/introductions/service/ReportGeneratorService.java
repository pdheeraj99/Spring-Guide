package io.mawa.spring.aop.introductions.service;

import org.springframework.stereotype.Service;

/**
 * A simple service that generates reports.
 * This is our target bean. We want to add UsageTrackable functionality
 * to this bean without modifying its code.
 */
@Service
public class ReportGeneratorService {

    public void generateReport(String reportName) {
        System.out.println("... Generating report: '" + reportName + "' ...");
        // Complex report generation logic would go here.
        System.out.println("... Report generated successfully.");
    }
}
