package io.mawa.spring.core.autowiring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("fieldService")
public class FieldAutowiredService {

    // The magic @Autowired annotation directly on the field
    @Autowired
    private CustomerRepository customerRepository;

    public FieldAutowiredService() {
        System.out.println("FieldAutowiredService created!");
    }

    public void serveCustomer(int id) {
        System.out.println("Serving customer (via field): " + customerRepository.findCustomerById(id));
    }
}
