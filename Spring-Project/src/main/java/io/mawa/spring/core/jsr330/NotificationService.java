package io.mawa.spring.core.jsr330;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named // This is the JSR-330 equivalent of @Component
public class NotificationService {

    // Using the standard @Inject and @Named annotations
    @Inject
    @Named("smsSvc")
    private MessageService messageService;

    @PostConstruct
    public void sendNotification() {
        System.out.println("--- Notification Service ---");
        String result = messageService.sendMessage("Your order has been shipped!");
        System.out.println(result);
    }
}
