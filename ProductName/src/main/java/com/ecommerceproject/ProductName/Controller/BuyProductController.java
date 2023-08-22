package com.ecommerceproject.ProductName.Controller;

import com.ecommerceproject.ProductName.Entity.BuyProduct;
import com.ecommerceproject.ProductName.Payload.BuyProductDto;
import com.ecommerceproject.ProductName.Service.BuyProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class BuyProductController {
    private final BuyProductService buyProductService;

    @Autowired
    public BuyProductController(BuyProductService buyProductService) {
        this.buyProductService = buyProductService;
    }

    @GetMapping
    public List<BuyProduct> getAllProducts() {
        return buyProductService.getAllProducts();
    }

    @GetMapping("/{id}")
    public BuyProduct getProductById(@PathVariable Long id) {
        return buyProductService.getProductById(id);
    }

    @PostMapping
    public BuyProduct createProduct(@RequestBody BuyProductDto productDto) {
        BuyProduct newProduct = new BuyProduct();
        newProduct.setName(productDto.getName());
        newProduct.setPrice(productDto.getPrice());
        return buyProductService.createProduct(newProduct);
    }

    @PutMapping("/{id}")
    public BuyProduct updateProduct(@PathVariable Long id, @RequestBody BuyProductDto productDto) {
        BuyProduct existingProduct = buyProductService.getProductById(id);
        if (existingProduct != null) {
            existingProduct.setName(productDto.getName());
            existingProduct.setPrice(productDto.getPrice());
            return buyProductService.updateProduct(id, existingProduct);
        }
        return null; // Or throw an exception indicating the product was not found
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        buyProductService.deleteProduct(id);
    }
}
