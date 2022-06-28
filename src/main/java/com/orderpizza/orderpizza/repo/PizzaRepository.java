package com.orderpizza.orderpizza.repo;

import com.orderpizza.orderpizza.models.Customer;
import com.orderpizza.orderpizza.models.Pizza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PizzaRepository extends CrudRepository <Pizza ,Long> {
//     Set<Customer> findCustomerById(Long id);
    Set<Pizza> findOrderByName(String name);

}
