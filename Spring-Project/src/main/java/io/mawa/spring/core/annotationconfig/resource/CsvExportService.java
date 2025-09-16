package io.mawa.spring.core.annotationconfig.resource;

public class CsvExportService implements ExportService {
    @Override
    public String export(String data) {
        return "Exporting '" + data + "' to CSV format. 📄";
    }
}
