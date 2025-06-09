package com.cognizant.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DriverDto {


    @Pattern(regexp="^[A-Z]{2}\\d{2}[A-Z]\\d{4}$", message="Auto Number must follow format: 'XX00X0000'.")
    private String autono;


    @Pattern(regexp="^[A-Z]{2}\\d{2} \\d{11}$", message="License Number must follow format: 'XX00 00000000000'.")
    private String license_no;

    private String name;

    private String id;

    @Pattern(regexp="^\\d{10}$", message="Phone Number should be exactly 10 digits")
    private String phone ;

    private String gender;

    @Pattern(regexp=".*@gmail\\.com$",message="Should end with @gmail.com")
    private String email;

    @NotNull(message = "Password is required.")
    @Size(min = 6, message = "Password must be at least 6 characters long.")
    private String password;

    @NotNull(message = "Confirm Password is required.")
    private String confirmpassword;


    public DriverDto(String id, String autono, String license_no, String name, String phone, String gender, String email) {
        this.id = id;
        this.autono = autono;
        this.license_no = license_no;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.email = email;
    }

    public DriverDto() {
    }
}
