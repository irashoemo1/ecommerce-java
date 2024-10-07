package com.ecommerce.Backend.config;

import com.ecommerce.Backend.common.ProductRepository;
import com.ecommerce.Backend.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
public class RunJsonDataLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(RunJsonDataLoader.class);
//    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    public RunJsonDataLoader(ProductRepository productRepository, ObjectMapper objectMapper) {
//        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        loadProductData();
    }

    private void loadProductData() {
        if (productRepository.count() == 0) {
            try (InputStream inputStream = getClass().getResourceAsStream("/data/products.json")) {
                List<Product> products = objectMapper.readValue(inputStream, new TypeReference<List<Product>>() {});
                logger.info("Product data loaded: {}", products);
                System.out.println("these are the products " + products);
                productRepository.saveAll(products);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load product data", e);
            }
        }
    }

}