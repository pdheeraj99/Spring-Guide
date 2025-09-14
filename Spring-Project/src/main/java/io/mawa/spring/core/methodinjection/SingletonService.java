package io.mawa.spring.core.methodinjection;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

@Service
public abstract class SingletonService {

    public void process() {
        // Get a new command instance each time
        PrototypeCommand command = createCommand();
        command.execute();
    }

    // This is the magic!
    // Spring will override this abstract method at runtime
    // to return a new instance of PrototypeCommand from the container.
    @Lookup
    protected abstract PrototypeCommand createCommand();
}
