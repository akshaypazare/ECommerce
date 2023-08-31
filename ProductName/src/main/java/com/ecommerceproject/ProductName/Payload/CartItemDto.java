package com.ecommerceproject.ProductName.Payload;

import com.ecommerceproject.ProductName.Entity.Product;
import lombok.Data;


@Data
public class CartItemDto {
    private Product product;
    private int quantity;

    public CartItemDto() {
        this.product = product;
        this.quantity = quantity;
    }

    public void setProductId(long id) {
        
    }

    public void setProductName(String name) {
    }


    // Getters and setters for product and quantity
}


