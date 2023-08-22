package com.ecommerceproject.ProductName.Repository;

import com.ecommerceproject.ProductName.Entity.BuyProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyProductRepository extends JpaRepository<BuyProduct, Long> {
    // You can add custom query methods here if needed
}
