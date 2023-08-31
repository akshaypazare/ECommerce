package com.ecommerceproject.ProductName.Repository;

import com.ecommerceproject.ProductName.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product,Long> {
    Optional<Product> findById(Long productId);

}

