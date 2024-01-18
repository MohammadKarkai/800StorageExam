package com.example.demo.model;

public class CartProductRequest {

    private Long clientId;
    private Long productId;

    public Long getClientId() {
        return clientId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
