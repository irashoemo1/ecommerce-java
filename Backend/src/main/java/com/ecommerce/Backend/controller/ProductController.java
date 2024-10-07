package com.ecommerce.Backend.controller;

import com.ecommerce.Backend.common.ProductRepository;
import com.ecommerce.Backend.model.Product;

import com.ecommerce.Backend.common.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        return ResponseEntity.ok(product);
    }

}