package io.mawa.spring.core.context.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    // This method ALSO listens for the same event.
    @EventListener
    public void sendNotification(PatientDischargedEvent event) {
        System.out.println("NotificationService received an event. Sending discharge notification for: " + event.getPatientName() + " ✉️");
        // ... notification logic here ...
    }
}
