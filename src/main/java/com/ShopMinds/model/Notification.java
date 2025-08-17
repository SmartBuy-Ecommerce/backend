package com.ShopMinds.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notification_id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationStatus is_read;
}
