package io.mawa.spring.core.env.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod") // This bean will only be created if the 'prod' profile is active
public class ProdDataSource implements DataSource {
    @Override
    public String getConnection() {
        return "Connected to PRODUCTION PostgreSQL Database!";
    }
}
