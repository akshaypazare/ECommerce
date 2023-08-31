package com.ecommerceproject.ProductName.Entity;

import lombok.Data;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartProductId;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    List<Product> products = new ArrayList<>();
    @OneToOne
    private User user;
    private Date creationDate;
    private double totalPrice;
    private int quantity;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();


    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void updateTotalPriceAndQuantity() {
        double total = 0;
        int itemCount = 0;

        for (CartItem cartItem : cartItems) {
            total += cartItem.getProduct().getPrice() * cartItem.getQuantity();
            itemCount += cartItem.getQuantity();
        }

        this.totalPrice = total;
        this.quantity = itemCount;
    }


    public void calculateTotalPrice() {
        double total = 0.0;
        for (CartItem cartItem : cartItems) {
            total += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        this.totalPrice = total;
    }

    public CartItem findCartItemById(long cartItemId) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getCartItemId() == cartItemId) {
                return cartItem;
            }
        }
        return null;

}