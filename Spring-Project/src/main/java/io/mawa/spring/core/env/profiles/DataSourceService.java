package io.mawa.spring.core.env.profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class DataSourceService {

    // Spring will inject the correct DataSource bean based on the active profile.
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void printDataSourceInfo() {
        System.out.println("--- DataSource Service ---");
        System.out.println("Using DataSource: " + dataSource.getConnection());
    }
}
