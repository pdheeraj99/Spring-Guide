package io.mawa.spring.core.javaconfig.composing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// A dummy DataSource class for demo purposes
class DataSource {
    public String getConnection() {
        return "Connected to DB!";
    }
}

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        return new DataSource();
    }
}
