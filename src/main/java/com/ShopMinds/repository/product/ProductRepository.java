package com.ShopMinds.repository.product;

import com.ShopMinds.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
//    Optional<Category> findByName(String name);
}
