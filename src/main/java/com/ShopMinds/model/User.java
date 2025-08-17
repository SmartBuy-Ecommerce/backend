package com.ShopMinds.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String phone;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;
//    private String profileImageName;
//    private String profileImageType;
//    @Lob
//    @Column(columnDefinition = "BYTEA")
//    private byte[] profileimageData;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_id")
    private CartItem cartItem;
}

