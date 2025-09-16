package io.mawa.spring.core.jsr330;

import jakarta.inject.Named;

@Named("smsSvc") // Using the standard @Named annotation
public class SmsService implements MessageService {
    @Override
    public String sendMessage(String message) {
        return "Sending SMS: '" + message + "' 📱";
    }
}
