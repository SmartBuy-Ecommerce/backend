package com.ShopMinds.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    @Column(nullable = false)
    private BigDecimal total_amount;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;
}
