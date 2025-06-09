package com.cognizant.service;

import com.cognizant.dto.DisplayDataDto;
import com.cognizant.dto.DriverDto;
import com.cognizant.entities.Driver;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DriverService {

    //Add the profile
    public DriverDto addDriverData(DriverDto driverdetail);

    //View the profile By Id
    public DriverDto getDriverDataById(String id);

    //View the profile By AutoNo -- -- Not used in frontend
    public DriverDto viewDriverData(String auto_no);

    //View the profile By emailn -- Not used in frontend
    public DisplayDataDto getDriverDataByEmail(String email);

    //Update the profile
    public DriverDto updateDriverData(String autono,DriverDto driverdetail);

    //Available Auto
    public  List<Driver> availableAutos();



}
