package com.example.demo.Controller;
import com.example.demo.model.Product;
import com.example.demo.model.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import java.util.stream.Collectors;


import java.util.List;

@RestController
@RequestMapping("/product")
public class  ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping("/fetchProducts")
    public ResponseEntity<?> fetchProducts(){
        try {
            List<Product> products = productService.FetchProducts();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();

            // Return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch products");
        }
    }
    @PostMapping("/createProduct")

    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getValidationErrors(bindingResult));
        }
        productService.CreateProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
    }

    @PostMapping("/editProduct")
    public ResponseEntity<String> EditProduct( @Valid @RequestBody Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getValidationErrors(bindingResult));
        }
        productService.EditProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
    }

    private String getValidationErrors(BindingResult bindingResult) {
        return bindingResult.getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining(", "));
    }

}
