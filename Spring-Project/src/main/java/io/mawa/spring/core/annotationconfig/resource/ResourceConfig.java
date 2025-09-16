package io.mawa.spring.core.annotationconfig.resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "io.mawa.spring.core.annotationconfig.resource")
public class ResourceConfig {

    @Bean(name = "csvExporter")
    public ExportService csvExportService() {
        return new CsvExportService();
    }

    @Bean(name = "jsonExporter")
    public ExportService jsonExportService() {
        return new JsonExportService();
    }
}
