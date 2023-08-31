package com.ecommerceproject.ProductName.Service;

import com.ecommerceproject.ProductName.Payload.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();

    ProductDto getByProductId(long id);

    void deleteProduct(long id);

    ProductDto updateProduct(ProductDto productDto, long id);
}
