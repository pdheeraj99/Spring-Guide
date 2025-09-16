package io.mawa.spring.core.annotationconfig.resource;

public class JsonExportService implements ExportService {
    @Override
    public String export(String data) {
        return "Exporting '" + data + "' to JSON format. {}";
    }
}
