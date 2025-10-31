package com.ShopMinds.dto;

import java.math.BigDecimal;

import com.ShopMinds.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String name;
    private Double price;
    private int quantity;
    private String category;
    private String description;
}
