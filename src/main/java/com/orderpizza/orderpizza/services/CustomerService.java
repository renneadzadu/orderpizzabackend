package com.orderpizza.orderpizza.services;

import com.orderpizza.orderpizza.models.Customer;
import com.orderpizza.orderpizza.repo.CustomerRepository;
import com.orderpizza.orderpizza.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    PizzaRepository pizzaRepository;

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public List<Customer> getAllCustomer() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    public Customer updateCustomer(Long customerId, Customer customer) {
        for (Customer c : getAllCustomer()) {
            if (c.getId() == customerId) {
                customerRepository.save(customer);
            }
        }
        return customer;
    }

    public Optional<Customer> getCustomer(Long id){
        return customerRepository.findById(id);
    }

    public  void  deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }

    public Set< Customer> getOrdersByCustomer(Long id) {
        return customerRepository.findCustomerById(id);
    }
}

