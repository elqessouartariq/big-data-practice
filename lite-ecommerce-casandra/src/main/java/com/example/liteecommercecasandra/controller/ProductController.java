package com.example.liteecommercecasandra.controller;


import com.example.liteecommercecasandra.entities.Product;
import com.example.liteecommercecasandra.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable UUID id) {
        return productService.getProduct(id);
    }
    @PostMapping
    public Product saveProduct(@RequestParam String name, @RequestParam Double price, @RequestParam int quantiteStock) {
        return productService.saveProduct(name, price, quantiteStock);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
    }

}
