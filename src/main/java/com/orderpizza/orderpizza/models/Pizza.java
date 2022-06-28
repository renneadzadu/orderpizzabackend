package com.orderpizza.orderpizza.models;

import com.orderpizza.orderpizza.status.PizzaStatus;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Pizza")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Pizza_id")
    private Long id;

    @Column(name = "Crust")
    private  String crust;

    @Column(name = "Topping")
    private String toppings;

    @Column(name = "Name")
    private  String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "pizzaStatus")
    private PizzaStatus PizzaStatus;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Pizza_id")
    @OrderBy
    private Set <Customer> customer;

    public Pizza() {
    }

    public Pizza(Long id, String crust, String toppings, String name, PizzaStatus pizzaStatus, Set<Customer> customer) {
        this.id = id;
        this.crust = crust;
        this.toppings = toppings;
        this.name = name;
        this.PizzaStatus = pizzaStatus;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public String getToppings() {
        return toppings;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PizzaStatus getPizzaStatus() {
        return PizzaStatus;
    }

    public void  setPizzaStatus(PizzaStatus pizzaStatus) {
        PizzaStatus = pizzaStatus;
    }

    public Set<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(Set<Customer> customer) {
        this.customer = customer;
    }


    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", crust='" + crust + '\'' +
                ", toppings='" + toppings + '\'' +
                ", name='" + name + '\'' +
                ", PizzaStatus=" + PizzaStatus +
                ", customer=" + customer +
                '}';
    }
}
