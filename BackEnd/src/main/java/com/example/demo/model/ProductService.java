package com.example.demo.model;

import com.example.demo.Repesotery.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    public List<Product> FetchProducts(){
        try {
            return productRepository.findAll();
        }catch(Exception ex){
            ex.printStackTrace();

        }
        return null;
    }

    public void CreateProduct(Product newproduct){
        try {
            productRepository.save(newproduct);
        }catch (Exception ex){
            ex.printStackTrace();

        }
    }

    public void EditProduct(Product updatedProduct)  {

        try {

            Long productId = updatedProduct.getId();
            System.out.println(productId);
            if (productId != null && productRepository.existsById(productId)) {
                productRepository.save(updatedProduct);
            } else {

                System.out.println("error in ID");
            }
        }catch(Exception ex){
            ex.printStackTrace();

        }
    }

}
