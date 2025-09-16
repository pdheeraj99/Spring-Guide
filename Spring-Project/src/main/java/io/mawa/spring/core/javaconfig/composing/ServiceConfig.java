package io.mawa.spring.core.javaconfig.composing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// A dummy service class for demo purposes
class MyService {
    private final DataSource dataSource;

    public MyService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void doWork() {
        System.out.println("MyService is working with DataSource: " + dataSource.getConnection());
    }
}


@Configuration
public class ServiceConfig {

    // Spring can inject the DataSource bean here because it will be
    // available in the context once we @Import both configs.
    @Bean
    public MyService myService(DataSource dataSource) {
        return new MyService(dataSource);
    }
}
