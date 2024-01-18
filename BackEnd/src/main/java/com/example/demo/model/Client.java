package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.lang.ref.Cleaner;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id ;
    @NotBlank(message="add first name")

    @JsonProperty("firstName")
    private String firstName;

    @NotBlank(message="add last name")
    @JsonProperty("lastName")
    private String lastName ;

    @Pattern(regexp = "^\\+961[0-9]{8}$")
    private String mobileNumber ;

    @OneToOne(mappedBy = "client",cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(mappedBy = "client")
    List<Order> orders;

    public Client(){

    }
    public Client(Long Id,String firstName ,String lastName, String mobileNumber){
        this.Id=Id;
        this.firstName=firstName ;
        this.lastName=lastName;
        this.mobileNumber=mobileNumber;

    }

    public Long getId() {
        return Id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public Cart getCart() {
        return cart;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
