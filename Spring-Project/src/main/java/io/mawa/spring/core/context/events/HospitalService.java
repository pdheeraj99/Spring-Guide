package io.mawa.spring.core.context.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void dischargePatient(String patientName) {
        System.out.println("Discharging patient: " + patientName);

        // Create the event object
        PatientDischargedEvent event = new PatientDischargedEvent(patientName);

        // Publish the event. Spring will take care of sending it to the listeners.
        System.out.println("Broadcasting PatientDischargedEvent... 📢");
        eventPublisher.publishEvent(event);

        System.out.println("Discharge process complete in HospitalService.");
    }
}
