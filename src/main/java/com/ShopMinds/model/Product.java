package com.ShopMinds.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="products")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;
    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,length = 1000)
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    private int quantity;
    private String image;
    private String imageType;
    @Lob
    private byte[] imageBytes;
}
