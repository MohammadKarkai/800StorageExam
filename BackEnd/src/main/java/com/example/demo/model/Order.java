package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    private Client client;

    @ManyToMany
    @JoinTable(name="order_item",
    joinColumns = @JoinColumn(name="order_id"),
    inverseJoinColumns = @JoinColumn(name="product_id"))
    private List<Product> productsss;

    public Order(){}

    public Order(Client client, List<Product> products){

        this.client=client;
        this.productsss=products;


    }

    public Long getId() {
        return Id;
    }

    public Client getClient() {
        return client;
    }

    public List<Product> getProducts() {
        return productsss;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setProducts(List<Product> products) {
        this.productsss = products;
    }
}
