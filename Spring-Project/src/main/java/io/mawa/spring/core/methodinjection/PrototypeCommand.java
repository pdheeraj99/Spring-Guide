package io.mawa.spring.core.methodinjection;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Scope("prototype")
public class PrototypeCommand {

    private final LocalDateTime creationTime;

    public PrototypeCommand() {
        this.creationTime = LocalDateTime.now();
    }

    public void execute() {
        System.out.println("Executing command created at: " + creationTime + " (hashCode: " + this.hashCode() + ")");
    }
}
