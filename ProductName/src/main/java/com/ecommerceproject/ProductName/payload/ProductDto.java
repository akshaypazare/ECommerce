package com.ecommerceproject.ProductName.payload;

import com.ecommerceproject.ProductName.Entity.Category;
import lombok.Data;

@Data
public class ProductDto {

    private long id;
    private String name;
    private String description;
    private Category category;


}
