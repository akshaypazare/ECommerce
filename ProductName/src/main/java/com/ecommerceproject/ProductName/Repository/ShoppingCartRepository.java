package com.ecommerceproject.ProductName.Repository;

import com.ecommerceproject.ProductName.Entity.CartItem;
import com.ecommerceproject.ProductName.Entity.ShoppingCart;
import com.ecommerceproject.ProductName.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {

    ShoppingCart findByUser(User authenticatedUser);

    List<ShoppingCart> findAl();
}
