package com.ShopMinds.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("ALL")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orderItems")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderItemId;
    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private BigDecimal totalPrice;
}
