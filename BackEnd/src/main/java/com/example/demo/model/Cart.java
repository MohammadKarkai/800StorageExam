package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id ;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany()
    @JoinTable(name="cart_item",
    joinColumns = @JoinColumn(name="cart_id"),
    inverseJoinColumns = @JoinColumn(name="product_id")
    )
    private List<Product> products;

    @OneToMany(mappedBy = "cart")
    List<CartItem> cartItems;



    public Cart(){
    }

    public Cart(Client client, List<Product> products){
        this.client = client;
        this.products=products;
    }

    public Long getId() {
        return Id;
    }
    public List<CartItem> getCartItems() {
        return cartItems;
    }
    public Client getClient() {
        return client;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
