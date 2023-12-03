package com.restapi.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author gerald barnes
 */
@Service
public class CustomerService{
    
    @Autowired
    private CustomerRepository customerRepository;
    
    public void saveCustomer(CustomerEntity customer){
        customerRepository.save(customer);
    }
    
    public CustomerEntity getCustomer(String customerRef){
        CustomerEntity customer = customerRepository.findByCustomerRef(customerRef);
        return customer;
    }
}