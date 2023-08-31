package com.ecommerceproject.ProductName.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class CartItem {
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;

    }

    // Getters and setters for product and quantity
}

