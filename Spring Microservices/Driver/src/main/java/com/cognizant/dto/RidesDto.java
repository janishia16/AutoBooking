package com.cognizant.dto;

import com.cognizant.entities.Driver;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class RidesDto {

    private String booking_id;

    private int no_of_seats;

    private LocalDate date;

    private LocalTime time;

    private String customername;  //Customer name

    private String phone; //Customer phone

    private String status;

}
