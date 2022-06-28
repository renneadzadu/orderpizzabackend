package com.orderpizza.orderpizza.controller;

import com.orderpizza.orderpizza.OrderpizzaApplication;
import com.orderpizza.orderpizza.models.Pizza;
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
@CrossOrigin("http://localhost:4200")
public class PizzaController {

    private static final Logger logger = LoggerFactory.getLogger(OrderpizzaApplication.class);

    @Autowired  //Dependency Injection
    private PizzaService pizzaService;

    @Autowired
    private CustomerService customerService;

    //C.R.U.D
    //Create - Post
    //Read - Get
    //Update - Put
    //Delete - delete

    @PostMapping("/orders")
    public ResponseEntity<?> addPizzaOrder(@Validated @RequestBody Pizza pizza){
        logger.info("Successfully ADDED an order with name :" + pizza);
        pizzaService.savePizzaOrder(pizza);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pizza.getId()).toUri();
        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
        //PizzaStatus pizzaStatus = PizzaStatus.STARTED;
    }


    @PutMapping("/orders/{id}")
    public ResponseEntity<Optional< Pizza>> updatePizzaOrder(@PathVariable Long id, @RequestBody Pizza pizza){
        logger.info("updating pizza order with id :" + id);
        return new ResponseEntity (this.pizzaService.updatePizzaOrder(id,pizza), HttpStatus.ACCEPTED);
    }

    //get a single pizza by id
    @GetMapping("/orders/{id}")
    public ResponseEntity<Optional<Pizza>> getPizzaById(@PathVariable Long id) {
        logger.info("successfully retrieved order with id :" + id);
        return new ResponseEntity<>(this.pizzaService.getPizzaOrder(id), HttpStatus.OK);
    }

    //come up w a delete functionality- so that we can delete a pizzaOrder
    @DeleteMapping("/orders/{id}")
    public  ResponseEntity<?> deletePizzaOrderById(@PathVariable Long id){
        logger.info("successfully DELETED an order from list with id: " +id);
         pizzaService.deletePizzaOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/orders")
    public ResponseEntity<List<Pizza>> getAllPizzaOrders(@RequestParam(required = false) String name){
        logger.info("Successfully retrieved all pizza order " );

        return new ResponseEntity<>( pizzaService.getAllPizzaOrder(),HttpStatus.OK);
    }

    @GetMapping("/orders/{id}/customer")
    public ResponseEntity<?> getAllCustomerPizzaOrderById(@PathVariable Long id) {
        logger.info("retrieving all customer from order " + id);
        Optional<Pizza> pizza = pizzaService.getPizzaOrder(id);
        return new ResponseEntity<>(pizzaService.getCustomerById(pizza.get().getId()) ,HttpStatus.OK);
    }

}
