package com.ShopMinds.dto;

import com.ShopMinds.model.UserRole;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private String name;
    private String email;
    private String password;
    private String phone;
    private UserRole role;
    private Date created_at;
}