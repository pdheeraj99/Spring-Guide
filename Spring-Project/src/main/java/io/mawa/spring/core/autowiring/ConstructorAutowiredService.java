package io.mawa.spring.core.autowiring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("constructorService")
public class ConstructorAutowiredService {

    private final CustomerRepository customerRepository;

    // The magic @Autowired annotation on the constructor
    @Autowired
    public ConstructorAutowiredService(CustomerRepository customerRepository) {
        System.out.println("ConstructorAutowiredService created!");
        this.customerRepository = customerRepository;
    }

    public void serveCustomer(int id) {
        System.out.println("Serving customer (via constructor): " + customerRepository.findCustomerById(id));
    }
}
