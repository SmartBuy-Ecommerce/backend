package com.ShopMinds.dto;


import com.ShopMinds.model.Category;
import lombok.Data;
@Data

public class ProductDto {
    private String name;
    private Double price;
    private int quantity;
    private String category;
    private String description;
}
