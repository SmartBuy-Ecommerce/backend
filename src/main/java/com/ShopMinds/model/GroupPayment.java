package com.ShopMinds.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "groupPayment")
public class GroupPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int group_id;
    @OneToMany
    @JoinColumn(name = "product_id", referencedColumnName = "group_id")
    private List<Product> products;
    @Column(nullable = false)
    private int amount;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus payementStatus;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;
}
