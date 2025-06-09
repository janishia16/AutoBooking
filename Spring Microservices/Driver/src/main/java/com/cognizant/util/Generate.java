package com.cognizant.util;

import com.cognizant.dto.DriverDto;
import com.cognizant.entities.Driver;
import com.cognizant.service.DriverService;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.time.Instant;
import java.util.List;
import java.util.Random;

@Component
public class Generate {

    @Autowired
    private DriverService driverService;

    public Driver getRandomAutoNumber(){
        List<Driver> avaiableAutos = driverService.availableAutos();

        // Randomly select and return an auto number
        Random random = new Random();
        return avaiableAutos.get(random.nextInt(avaiableAutos.size()));
    }

    //Generate Driver_Id
    public static String generateDriverId() {
        String randomString = RandomStringUtils.randomAlphanumeric(2);
        long timestamp = Instant.now().toEpochMilli();
        String driverId = ("AT" +randomString + (timestamp % 100)).toUpperCase();
        return driverId;
    }

    //Generate Driver_Id
    public  String generateBookingId() {
        String randomString = RandomStringUtils.randomAlphanumeric(2);
        long timestamp = Instant.now().toEpochMilli();
        String bookingId = ("BK" +randomString + (timestamp % 100)).toUpperCase();
        return bookingId;
    }
}
