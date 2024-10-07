package com.ecommerce.Backend.common;

import com.ecommerce.Backend.model.Product;

import com.ecommerce.Backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory(String category);
}