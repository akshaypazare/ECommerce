package com.ecommerceproject.ProductName.Repository;

import com.ecommerceproject.ProductName.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {

}
