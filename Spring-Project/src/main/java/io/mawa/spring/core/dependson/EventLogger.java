package io.mawa.spring.core.dependson;

public class EventLogger {

    public EventLogger() {
        System.out.println("Step 1: EventLogger has been initialized! ✍️");
    }

    public void log(String message) {
        System.out.println("LOG: " + message);
    }
}
