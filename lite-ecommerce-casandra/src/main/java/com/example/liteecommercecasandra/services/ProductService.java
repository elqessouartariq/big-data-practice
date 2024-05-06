package com.example.liteecommercecasandra.services;

import com.example.liteecommercecasandra.entities.Product;
import com.example.liteecommercecasandra.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Product saveProduct(String name, Double price, int quantiteStock) {
        return productRepository.save(new Product(UUID.randomUUID(), name, price, quantiteStock));
    }
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
    public Product getProduct(UUID id) {
        return productRepository.findById(id).orElse(null);
    }
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
}
