package com.orderpizza.orderpizza.controller;

import com.orderpizza.orderpizza.OrderpizzaApplication;
import com.orderpizza.orderpizza.models.Customer;
import com.orderpizza.orderpizza.services.CustomerService;
import com.orderpizza.orderpizza.services.PizzaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(OrderpizzaApplication.class);

    @Autowired  //Dependency Injection
    private CustomerService customerService;

    @Autowired
    private PizzaService pizzaService;

    //C.R.U.D
    //Create - Post
    //Read - Get
    //Update - Put
    //Delete - delete

    @PostMapping("/Customer")
    public ResponseEntity<?> addCustomer(@Validated @RequestBody Customer customer){
        logger.info("Successfully ADDED an order with name :" + customer);
        customerService.saveCustomer(customer);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId()).toUri();
        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    @PutMapping("/customer/{id}")
    public ResponseEntity<Optional< Customer>> updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
        logger.info("updating customer order with id :" + id);
        return new ResponseEntity (this.customerService.updateCustomer(id,customer), HttpStatus.ACCEPTED);
    }

    //get a single customer by id
    @GetMapping("/customer/{id}")
    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable Long id) {
        logger.info("successfully retrieved order with id :" + id);
        return new ResponseEntity<>(this.customerService.getCustomer(id), HttpStatus.OK);
    }

    //come up w a delete functionality- so that we can delete a pizzaOrder
    @DeleteMapping("/customer/{id}")
    public  ResponseEntity<?> deleteCustomerById(@PathVariable Long id){
        logger.info("successfully DELETED an order from list with id: " +id);
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> getAllCustomer(@RequestParam(required = false) Long id){
//        if(id != null){
//            return new ResponseEntity<>(customerRepository.findCustomerById(id),HttpStatus.OK);
//        }
        logger.info("Successfully retrieved all customer order " );

        return new ResponseEntity<>( customerService.getAllCustomer(),HttpStatus.OK);
    }

    @GetMapping("/customer/{id}/orders")
    public ResponseEntity<?> getAllOrdersByCustomerId(@PathVariable Long id) {
        logger.info("Successfully retrieved all orders for customer " + id);
        return new ResponseEntity<>(customerService.getOrdersByCustomer(id) ,HttpStatus.OK);
    }

//    @RequestMapping(value="/customer/{id}/orders", method=RequestMethod.GET)
//    public Optional<Pizza> getAllOrdersByCustomerId(@PathVariable Long id) {
//        customerRepository.findCustomerById(id);
//        return pizzaService.getPizzaOrder(id);
//}


}
