package com.orderpizza.orderpizza.services;

import com.orderpizza.orderpizza.models.Customer;
import com.orderpizza.orderpizza.models.Pizza;
import com.orderpizza.orderpizza.repo.CustomerRepository;
import com.orderpizza.orderpizza.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public void savePizzaOrder(Pizza pizza) {
        pizzaRepository.save(pizza);
    }

    public List<Pizza> getAllPizzaOrder() {
        List<Pizza> pizzas = new ArrayList<>();
        pizzaRepository.findAll().forEach(pizzas::add);
        return pizzas;
    }

    public Pizza updatePizzaOrder(Long pizzaId, Pizza pizza) {
        for (Pizza p : getAllPizzaOrder()) {
            if (p.getId() == pizzaId) {
                pizzaRepository.save(pizza);
            }
        }
        return pizza;
    }

    public Optional<Pizza> getPizzaOrder(Long id){
        return pizzaRepository.findById(id);
    }

    public  void  deletePizzaOrder(Long id){
        pizzaRepository.deleteById(id);
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

}