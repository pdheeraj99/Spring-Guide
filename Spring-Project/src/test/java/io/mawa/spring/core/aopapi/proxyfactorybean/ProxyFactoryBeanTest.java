package io.mawa.spring.core.aopapi.proxyfactorybean;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.context.ApplicationContext;

@SpringJUnitConfig(locations = "classpath:pfb-context.xml")
class ProxyFactoryBeanTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Autowired
    private ApplicationContext context;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void proxyFactoryBeanShouldCreateAOPProxy() {
        // Get the bean directly from the context to avoid autowiring ambiguity
        DocumentService documentService = (DocumentService) context.getBean("documentService");
        assertThat(documentService).isNotNull();

        // Call the method on the proxy
        documentService.readDocument("Test.txt");

        String output = outContent.toString();

        // Verify that the SimpleDebugInterceptor output is present, which proves the proxy worked
        assertThat(output).contains("SimpleDebugInterceptor: Before method 'readDocument'");
        assertThat(output).contains("SimpleDebugInterceptor: After method 'readDocument'");
    }
}
