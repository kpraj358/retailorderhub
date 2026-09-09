package com.training.retailorderhub.repository;

import com.training.retailorderhub.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
