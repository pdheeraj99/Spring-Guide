package io.mawa.spring.core.autowiring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("setterService")
public class SetterAutowiredService {

    private CustomerRepository customerRepository;

    public SetterAutowiredService() {
        System.out.println("SetterAutowiredService created!");
    }

    // The magic @Autowired annotation on the setter method
    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        System.out.println("Setter method called for CustomerRepository!");
        this.customerRepository = customerRepository;
    }

    public void serveCustomer(int id) {
        System.out.println("Serving customer (via setter): " + customerRepository.findCustomerById(id));
    }
}
