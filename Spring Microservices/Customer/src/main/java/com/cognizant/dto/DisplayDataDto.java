package com.cognizant.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class DisplayDataDto {

    @Id
    private String id;

    private String name;

    private String phone;

    private String email;

    private String gender;

    private String password;




}
