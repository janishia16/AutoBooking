package com.cognizant.dto;

import jakarta.persistence.Id;
import lombok.Data;


@Data
public class DisplayDataDto {

    @Id
    private String id;

    private String name;

    private String phone;

    private String email;

    private String gender;




}
