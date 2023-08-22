package com.ecommerceproject.ProductName.Controller;

import com.ecommerceproject.ProductName.Service.Impl.ProductServiceimpl;
import com.ecommerceproject.ProductName.Service.ProductService;
import com.ecommerceproject.ProductName.payload.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
    }

    @GetMapping("/showproduct")
    public List<ProductDto> getAllproducts(){
        List<ProductDto> allProducts = productService.getAllProducts();
        return allProducts;

    }
    @GetMapping("/{id}/showproduct")
    public ResponseEntity<ProductDto> getById(@PathVariable ("id")long id){
        return new ResponseEntity<>(productService.getByProductId(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product deleted successfully",HttpStatus.OK) ;

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto,@PathVariable ("id")long id){
        return new ResponseEntity<>(productService.updateProduct(productDto,id),HttpStatus.OK);
    }




}
