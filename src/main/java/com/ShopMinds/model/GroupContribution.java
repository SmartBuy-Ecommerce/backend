package com.ShopMinds.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "group_contributions")
public class GroupContribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contributionId;

    private Double amountPaid;
    private String paymentStatus;
    private LocalDateTime contributedAt;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupPayment groupPayment;

    @ManyToOne
    @JoinColumn(name = "contributor_id")
    private User contributor;

    // Getters and setters
}
