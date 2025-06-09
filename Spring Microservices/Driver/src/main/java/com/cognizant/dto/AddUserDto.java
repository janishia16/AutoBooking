package com.cognizant.dto;

import lombok.Data;

@Data
public class AddUserDto {
    private String email;
    private String password;
    private String id;
    private String role;
}
