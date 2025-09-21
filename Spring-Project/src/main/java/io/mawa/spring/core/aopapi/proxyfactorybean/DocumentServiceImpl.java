package io.mawa.spring.core.aopapi.proxyfactorybean;

public class DocumentServiceImpl implements DocumentService {

    @Override
    public String readDocument(String documentName) {
        System.out.println("Reading document: " + documentName);
        return "Contents of " + documentName;
    }
}
