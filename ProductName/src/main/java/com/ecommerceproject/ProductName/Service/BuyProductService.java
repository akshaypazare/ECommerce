package com.ecommerceproject.ProductName.Service;

import com.ecommerceproject.ProductName.Entity.BuyProduct;

import java.util.List;

public interface BuyProductService {
    List<BuyProduct> getAllProducts();
    BuyProduct getProductById(Long id);
    BuyProduct createProduct(BuyProduct product);
    BuyProduct updateProduct(Long id, BuyProduct updatedProduct);
    void deleteProduct(Long id);
}

