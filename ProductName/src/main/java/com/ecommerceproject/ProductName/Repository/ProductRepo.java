package com.ecommerceproject.ProductName.Repository;

import com.ecommerceproject.ProductName.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Long> {
}
