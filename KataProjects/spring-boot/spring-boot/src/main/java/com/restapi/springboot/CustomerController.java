package com.restapi.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gerald barnes
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    
    @PostMapping
    public ResponseEntity<String> saveCustomer(@RequestBody CustomerEntity customer){
        customerService.saveCustomer(customer);
        return new ResponseEntity<>("Customer saved successfully", HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<CustomerEntity> getCustomer(@RequestBody String customerRef){
        CustomerEntity customer = customerService.getCustomer(customerRef);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
}
