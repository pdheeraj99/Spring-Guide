package io.mawa.spring.core.env.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev") // This bean will only be created if the 'dev' profile is active
public class DevDataSource implements DataSource {
    @Override
    public String getConnection() {
        return "Connected to DEV H2 Database!";
    }
}
