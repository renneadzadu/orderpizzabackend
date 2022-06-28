package com.orderpizza.orderpizza.repo;

import com.orderpizza.orderpizza.models.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {
    Set<Customer> findCustomerById(Long id);
}
