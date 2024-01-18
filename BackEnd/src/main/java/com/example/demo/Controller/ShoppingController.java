package com.example.demo.Controller;

import com.example.demo.model.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class ShoppingController {

    private final shoppingService shoppingService ;

    @Autowired
    public ShoppingController(shoppingService shoppingService){
        this.shoppingService=shoppingService;
    }

    @PostMapping("/addToCart")
    public ResponseEntity<?> addToCart(@RequestBody CartProductRequest request){
        Long clientId = request.getClientId();
        Long productId = request.getProductId();
        Cart cart = shoppingService.addToCart(clientId, productId);
        if(cart==null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add To Cart");
        }
        return ResponseEntity.ok("Product added to Cart Succesfully");
    }

    @PostMapping("/addOrder")
    public ResponseEntity<?> addOrder(@RequestBody OrderRequest request){
        Long clientId=request.getClientId();
        Order order =shoppingService.addOrder((clientId));
        if(order==null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add Order");
        }
        return ResponseEntity.ok("Order addedSuccesfully");

    }



}
