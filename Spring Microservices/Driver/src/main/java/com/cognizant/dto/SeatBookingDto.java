package com.cognizant.dto;

import com.cognizant.entities.Driver;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class SeatBookingDto {


    private String booking_id;

    private Driver driver;

    private String autono;

    private int no_of_seats;

    private LocalDate date;

    private LocalTime time;

    private String id;

    private String status;

}
