package com.ecommerceproject.ProductName.Service.Impl;

import com.ecommerceproject.ProductName.Entity.Product;
import com.ecommerceproject.ProductName.Exception.ResourceNotFoundException;
import com.ecommerceproject.ProductName.Repository.ProductRepo;
import com.ecommerceproject.ProductName.Service.ProductService;
import com.ecommerceproject.ProductName.payload.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceimpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        //convert dto to entity
       Product product= mapToEntity(productDto);
        Product product1 = productRepo.save(product);

        //convert entity to dto
           ProductDto productDto1=mapToDto(product1);
        return productDto1;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepo.findAll();
        return products.stream().map(product -> mapToDto(product)).collect(Collectors.toList());

    }

    @Override
    public ProductDto getByProductId(long id) {
        Product product = productRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product", "productId", id)
        );
        return mapToDto(product);
    }

    @Override
    public void deleteProduct(long id) {

        Product product = productRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product", "id", id)
        );
        productRepo.delete(product);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, long id) {
        Product product = productRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("product", "id", id)
        );
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setCategory(productDto.getCategory());

        Product product1 = productRepo.save(product);

        return mapToDto(product1);

    }

    private ProductDto mapToDto(Product product) {
        ProductDto productDto=new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory());
        return productDto;
    }

    private Product mapToEntity(ProductDto productDto) {
        Product product=new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setCategory(productDto.getCategory());
        return product;
    }
}
