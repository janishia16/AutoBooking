package com.cognizant.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Entity
@Table(name="customer")
@Data
public class Customer {

    @Id
    @Column(name="id")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="phone")
    private String phone ;

    @Column(name="email")
    private String email;

    @Column(name="gender")
    private String gender;

    private String password;

    private String confirmpassword;



}
