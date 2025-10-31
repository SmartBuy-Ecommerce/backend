package com.ShopMinds.repository.product;

import com.ShopMinds.model.Category;
import com.ShopMinds.model.Product;
import com.ShopMinds.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Category> findByName(String name);
}
