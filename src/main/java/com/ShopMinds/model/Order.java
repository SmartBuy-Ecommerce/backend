package com.ShopMinds.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private Users user;
    @Column(nullable = false)
    private BigDecimal total_amount;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;
}
