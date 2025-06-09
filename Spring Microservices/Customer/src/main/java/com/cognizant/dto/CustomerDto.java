package com.cognizant.dto;

import com.cognizant.entities.Payment;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

import java.util.List;


@Data
public class CustomerDto {

    @Id
    @Size(min=6,max=6)
    private String id;

    @Size(min=3,message="Name should be more than 2 letters")
    private String name;

    @NotNull
    @Pattern(regexp="^\\d{10}$", message="Phone Number should be exactly 10 digits")
    private String phone;

    @Pattern(regexp=".*@gmail\\.com$",message="Should end with @gmail.com")
    private String email;

    private String gender;

    @NotNull(message = "Password is required.")
    @Size(min = 6, message = "Password must be at least 6 characters long.")
    private String password;

    @NotNull(message = "Confirm Password is required.")
    private String confirmpassword;



}
