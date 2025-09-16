package io.mawa.spring.core.classpathscanning;

import org.springframework.stereotype.Repository;

@Repository // A specialization of @Component for the persistence layer
public class MyRepository {
    public String getData() {
        return "Getting data from MyRepository!";
    }
}
