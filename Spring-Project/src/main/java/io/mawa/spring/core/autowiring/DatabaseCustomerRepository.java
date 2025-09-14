package io.mawa.spring.core.autowiring;

import org.springframework.stereotype.Repository;

@Repository
public class DatabaseCustomerRepository implements CustomerRepository {
    @Override
    public String findCustomerById(int id) {
        // Imagine this connects to a real database
        return "Customer with ID: " + id;
    }
}
