package com.restapi.springboot;

import org.springframework.data.repository.CrudRepository;
/**
 * @author gerald barnes
 */
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long>{
    CustomerEntity findByCustomerRef(String customerRef);
}
