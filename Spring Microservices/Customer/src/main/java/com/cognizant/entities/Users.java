package com.cognizant.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="users")
@Data
public class Users {

    @Id
    private String email;
    private String password;
    private String role;
    private boolean isLogin;
    private String userid;

}
