package io.mawa.spring.core.context.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class BillingService {

    // This method will automatically be called when a PatientDischargedEvent is published.
    @EventListener
    public void generateBill(PatientDischargedEvent event) {
        System.out.println("BillingService received an event. Generating final bill for patient: " + event.getPatientName() + " 🧾");
        // ... billing logic here ...
    }
}
