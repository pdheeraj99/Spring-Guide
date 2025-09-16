package io.mawa.spring.core.context.events;

// This is our custom event. It's just a simple POJO.
// It can optionally extend ApplicationEvent, but it's not required for modern Spring.
public class PatientDischargedEvent {

    private final String patientName;

    public PatientDischargedEvent(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientName() {
        return patientName;
    }
}
