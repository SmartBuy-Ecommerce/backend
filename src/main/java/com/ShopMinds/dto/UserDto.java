package com.ShopMinds.dto;

import com.ShopMinds.model.UserRole;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserDto {
    private String name;
    private String email;
    private String password;
    private BigDecimal phone;
    private String role;
    private Date created_at;
    private String Status;
}