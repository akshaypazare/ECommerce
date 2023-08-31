package com.ecommerceproject.ProductName.Service;

import com.ecommerceproject.ProductName.Payload.CartItemDto;
import com.ecommerceproject.ProductName.Payload.ProductDto;
import com.ecommerceproject.ProductName.Payload.ShoppingCartDto;

import java.util.List;

public interface ShoppingCartService {


     List<CartItemDto> getProductsInCart() ;


    boolean addToCart(Long productId, int quantity);



    void deleteProductFromCart(long productId);


    ProductDto updateProductQuantityInCart(long productId, int quantity);
}

