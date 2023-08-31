package com.ecommerceproject.ProductName.Controller;

import com.ecommerceproject.ProductName.Entity.CartItem;
import com.ecommerceproject.ProductName.Entity.ShoppingCart;
import com.ecommerceproject.ProductName.Payload.CartItemDto;
import com.ecommerceproject.ProductName.Payload.ProductDto;
import com.ecommerceproject.ProductName.Service.ProductService;
import com.ecommerceproject.ProductName.Service.ShoppingCartService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@Api(tags = "Shopping Cart API", description = "Endpoints for managing the shopping cart")
public class ShoppingCartController {
@Autowired
    private ShoppingCartService cartService;

    @Autowired
    private ProductService productService;




    @PostMapping("/add")
    @ApiOperation(
            value = "Add a product to the shopping cart",
            notes = "Adds the specified quantity of a product to the user's shopping cart."
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Product added to cart successfully"),
            @ApiResponse(code = 400, message = "Invalid request, quantity must be greater than 0.")
    })
    public ResponseEntity<String> addToCart(
            @ApiParam(value = "ID of the product to add", required = true) @RequestParam Long productId,
            @ApiParam(value = "Quantity of the product to add", required = true) @RequestParam int quantity) {

        if (quantity <= 0) {
            return ResponseEntity.badRequest().body("Quantity must be greater than 0.");
        }

        boolean added = cartService.addToCart(productId, quantity);

        if (added) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Product added to cart.");
        } else {
            return ResponseEntity.badRequest().body("Failed to add product to cart.");
        }
    }

    @GetMapping("/cart/products")
    @ApiOperation(
            value = "Get products in the shopping cart",
            notes = "Retrieves all products in the user's shopping cart."
    )
    public ResponseEntity<List<CartItemDto>> getProductsInCart() {
        List<CartItemDto> cartItemDTOs = cartService.getProductsInCart();
        return ResponseEntity.ok(cartItemDTOs);
    }



    @DeleteMapping("/products/{productId}")
    @ApiOperation(value = "Delete a product from the cart", notes = "Removes a product from the shopping cart.")
    public ResponseEntity<String> deleteProductFromCart(
            @ApiParam(value = "ID of the product to be removed", required = true)
            @PathVariable("productId") long productId) {
        cartService.deleteProductFromCart(productId);
        return new ResponseEntity<>("Product removed from the cart", HttpStatus.OK);
    }

}
