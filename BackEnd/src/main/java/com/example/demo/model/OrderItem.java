package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name="order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order ;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @Column(nullable = false, columnDefinition = "int default 1")
    private int quantity;

    @Column(nullable = false, columnDefinition = "int default 0")
    private double price;

    public OrderItem(){}
    public OrderItem(Order order, Product product , int quantity){
        this.order=order;
         this.product=product;
         this.quantity=quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return Id;
    }

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
