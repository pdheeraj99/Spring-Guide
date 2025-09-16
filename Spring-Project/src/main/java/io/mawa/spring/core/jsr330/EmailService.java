package io.mawa.spring.core.jsr330;

import jakarta.inject.Named;

@Named("emailSvc") // Using the standard @Named annotation to give this bean a name
public class EmailService implements MessageService {
    @Override
    public String sendMessage(String message) {
        return "Sending Email: '" + message + "' 📧";
    }
}
