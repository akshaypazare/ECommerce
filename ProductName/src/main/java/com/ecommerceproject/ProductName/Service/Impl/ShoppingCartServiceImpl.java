package com.ecommerceproject.ProductName.Service.Impl;

import com.ecommerceproject.ProductName.Entity.CartItem;
import com.ecommerceproject.ProductName.Entity.Product;
import com.ecommerceproject.ProductName.Entity.ShoppingCart;
import com.ecommerceproject.ProductName.Entity.User;
import com.ecommerceproject.ProductName.Exception.ResourceNotFoundException;
import com.ecommerceproject.ProductName.Payload.CartItemDto;
import com.ecommerceproject.ProductName.Payload.ProductDto;
import com.ecommerceproject.ProductName.Payload.ShoppingCartDto;
import com.ecommerceproject.ProductName.Repository.ProductRepo;
import com.ecommerceproject.ProductName.Repository.ShoppingCartRepository;
import com.ecommerceproject.ProductName.Service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductRepo productRepo;




    @Override
    public boolean addToCart(Long productId, int quantity) {
        // Fetch the product from the database
        Product product = getProductFromDatabase(productId);

        if (product == null) {
            return false; // Product not found, cannot add to cart
        }

        // Get the current shopping cart for the user (you need to implement this logic)
        ShoppingCart shoppingCart = getCurrentUserShoppingCart();

        // Create a CartItem instance and add it to the cart
        CartItem cartItem = new CartItem(product, quantity);
        shoppingCart.getCartItems().add(cartItem); // Add the cart item to the shopping cart

        // Update shopping cart's total price and quantity
        shoppingCart.updateTotalPriceAndQuantity();

        // Save the updated shopping cart
        shoppingCartRepository.save(shoppingCart);

        return true; // Product added to cart successfully
    }



@Override
public List<CartItemDto> getProductsInCart() {
    List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();
    List<CartItem> cartItems = shoppingCarts.stream()
            .flatMap(shoppingCart -> shoppingCart.getCartItems().stream())
            .collect(Collectors.toList());

    return cartItems.stream()
            .map(this::mapToCartItemDto)
            .collect(Collectors.toList());
    }
    private CartItemDto mapToCartItemDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProductId(cartItem.getProduct().getId());
        cartItemDto.setProductName(cartItem.getProduct().getName());
        cartItemDto.setQuantity(cartItem.getQuantity());
        // Set other DTO properties as needed
        return cartItemDto;

    }

    @Override
    public void deleteProductFromCart(long productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        productRepo.delete(product);
    }

    private Product getProductFromDatabase(Long productId) {
        Optional<Product> productOptional = productRepo.findById(productId);
        return productOptional.orElse(null);
    }


    private ShoppingCart getCurrentUserShoppingCart() {


        // Get the authentication object from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Ensure the authenticated principal is of type User
        if (!(authentication.getPrincipal() instanceof User)) {
            throw new IllegalStateException("Authentication principal is not of type User");
        }

        // Extract the authenticated user from the authentication object
        User authenticatedUser = (User) authentication.getPrincipal();

        // Check if the user already has a shopping cart
        ShoppingCart existingCart = shoppingCartRepository.findByUser(authenticatedUser);

        // If no cart exists, create a new one
        if (existingCart == null) {
            existingCart = new ShoppingCart();
            existingCart.setUser(authenticatedUser);
            existingCart.setCreationDate(new Date());

            // Additional initialization logic for the cart can be added here if needed

            // Save the new cart to the repository
            shoppingCartRepository.save(existingCart);
        }

        return existingCart;
    }
}




