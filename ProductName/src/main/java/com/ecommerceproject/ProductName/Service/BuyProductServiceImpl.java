package com.ecommerceproject.ProductName.Service;

import com.ecommerceproject.ProductName.Entity.BuyProduct;
import com.ecommerceproject.ProductName.Repository.BuyProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyProductServiceImpl implements BuyProductService {
    private final BuyProductRepository buyProductRepository;

    @Autowired
    public BuyProductServiceImpl(BuyProductRepository buyProductRepository) {
        this.buyProductRepository = buyProductRepository;
    }

    @Override
    public List<BuyProduct> getAllProducts() {
        return buyProductRepository.findAll();
    }

    @Override
    public BuyProduct getProductById(Long id) {
        return buyProductRepository.findById(id).orElse(null);
    }

    @Override
    public BuyProduct createProduct(BuyProduct product) {
        return buyProductRepository.save(product);
    }

    @Override
    public BuyProduct updateProduct(Long id, BuyProduct updatedProduct) {
        BuyProduct existingProduct = buyProductRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            return buyProductRepository.save(existingProduct);
        }
        return null; // Or throw an exception indicating the product was not found
    }

    @Override
    public void deleteProduct(Long id) {
        buyProductRepository.deleteById(id);
    }

    // Add more methods as needed for your specific use case
}

