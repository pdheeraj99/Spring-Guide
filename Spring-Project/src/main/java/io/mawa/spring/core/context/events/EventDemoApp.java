package io.mawa.spring.core.context.events;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EventDemoApp {
    public static void main(String[] args) {
        System.out.println("--- Starting the Spring Container ---");
        ApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
        System.out.println("--- Container started successfully! ---");

        System.out.println("\n--- Getting the HospitalService and discharging a patient ---");
        HospitalService hospitalService = context.getBean(HospitalService.class);
        hospitalService.dischargePatient("Mawa");

        System.out.println("\n--- Demo Complete ---");
    }
}
