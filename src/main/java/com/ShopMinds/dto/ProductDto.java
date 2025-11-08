package com.ShopMinds.dto;


import lombok.Data;
@Data

public class ProductDto {
    private String name;
    private Double price;
    private int quantity;
    private String category;
    private String description;
    private String imageUrl;


}


