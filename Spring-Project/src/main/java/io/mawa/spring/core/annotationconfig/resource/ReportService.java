package io.mawa.spring.core.annotationconfig.resource;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;

@Service
public class ReportService {

    // Here, we are specifically asking for the bean named "jsonExporter".
    // Spring will find the bean with this name and inject it.
    @Resource(name = "jsonExporter")
    private ExportService exportService;

    @PostConstruct
    public void generateReport() {
        System.out.println("--- Report Service ---");
        String reportData = "Monthly Sales Data";
        System.out.println("Generating report...");
        String result = exportService.export(reportData);
        System.out.println(result);
    }
}
