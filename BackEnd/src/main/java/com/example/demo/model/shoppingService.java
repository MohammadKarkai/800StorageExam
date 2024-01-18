package com.example.demo.model;

import com.example.demo.Repesotery.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.logging.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class shoppingService {

    private final ClientRepository clientRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository ;
    private final ProductRepository productRepository;
    private static final Logger logger = LogManager.getLogger(shoppingService.class);


    @Autowired
    public shoppingService(ClientRepository clientRepository, CartRepository cartRepository, CartItemRepository cartItemRepository, OrderItemRepository orderItemRepository,OrderRepository orderRepository,ProductRepository productRepository){
        this.clientRepository=clientRepository;
        this.cartRepository=cartRepository;
        this.cartItemRepository=cartItemRepository;
        this.orderRepository=orderRepository;
        this.orderItemRepository=orderItemRepository;
        this.productRepository=productRepository;
    }

    @Transactional
    public Cart addToCart(Long clientId, Long productId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);
        if(product.getQuantity()-1<=0){
            return null;
        }
        if (client == null) {
            return null;
        }
        Cart cart = client.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setClient(client);
            client.setCart(cart);
            cartRepository.save(cart);

        }

        CartItem cartItem= cartItemRepository.findByCart_IdAndProduct_Id(cart.getId(),productId);


        if(cartItem==null) {
            CartItem newCartItem = new CartItem(cart, product, 1);
            cartItemRepository.save(newCartItem);
            if(cart.getProducts()==null){
                List<Product> productList=new ArrayList<Product>();
                productList.add(product);
                cart.setProducts(productList);

            }
            else{
                cart.getProducts().add(product);
            }

        }else{
            cartItem.setQuantity(cartItem.getQuantity()+1);
            cartItemRepository.save(cartItem);
        }

        return cart;
    }

    @Transactional
    public Order addOrder(Long clientId){
        Client client =clientRepository.findById(clientId).orElse(null);
        if(client==null){
            return null;
        }
        Cart cart=client.getCart();
        if(cart==null){
            return null;
        }
        List<Product> products=cart.getProducts();
        List<CartItem> cartItems=cartItemRepository.findByCart_Id(cart.getId());
        System.out.println(cart.getId());
        client.setCart(null);
        cart.setCartItems(null);
        cartRepository.deleteAll();

        Order order=new Order(client,products);
        System.out.println(order.getProducts());
        orderRepository.save(order);
        if(client.getOrders()==null){
            List<Order>orderList=new ArrayList<Order>();
            orderList.add(order);
            client.setOrders(orderList);
        }
        client.getOrders().add(order);

         List<OrderItem> orderItems = orderItemRepository.findByOrder_Id(order.getId());
         List<OrderItem> newOrderItems = new ArrayList<OrderItem>();
        for(OrderItem orderItem:orderItems){
            CartItem cartItem=cartItems.stream().filter(c-> c.getProduct().equals(orderItem.getProduct())).findFirst().orElse(null);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(orderItem.getProduct().getPrice()*orderItem.getQuantity());
            newOrderItems.add(orderItem);
        }

        logger.info("Order added: " + order.getId());
        logger.info("Client orders count: " + client.getOrders().size());

        UpdateInventory(clientId);

        return order ;

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void UpdateInventory(Long clientId){
        logger.info("Updated inventory for client: " + clientId);

        Client client=clientRepository.findById(clientId).orElse(null);

        if(client==null){
            return;
        }
        List<Order> orders= orderRepository.findByClient_Id(clientId);
        Order order=orders.stream().max(Comparator.comparing(Order::getId)).orElse(null);
        if(order ==null){
            return;
        }
        List<OrderItem> orderItemList=orderItemRepository.findByOrder_Id(order.getId());
        System.out.println(orderItemList);
        List<Product> productList =new ArrayList<Product>();

        for(OrderItem orderItem:orderItemList){
            Product product= orderItem.getProduct();
            product.setQuantity(product.getQuantity()-orderItem.getQuantity());
            productList.add(product);

        }

        productRepository.saveAll(productList);


    }

}
