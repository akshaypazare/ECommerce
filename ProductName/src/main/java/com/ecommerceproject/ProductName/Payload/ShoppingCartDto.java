package com.ecommerceproject.ProductName.Payload;

import com.ecommerceproject.ProductName.Entity.Product;
import lombok.Data;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
public class ShoppingCartDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartProductId;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    List<Product> products = new ArrayList<>();
    private User user;
    private Date creationDate;
    private double totalPrice;
    private int quantity;
}


