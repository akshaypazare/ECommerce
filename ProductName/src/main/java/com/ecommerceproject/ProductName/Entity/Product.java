package com.ecommerceproject.ProductName.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Locale;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private long id;
    @Column(name = "productName",nullable = false)
    private String name;
    @Column(name="productDescription", nullable = false)
    private String description;
    @Column(name = "productCategory",nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

}
