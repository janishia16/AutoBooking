package com.cognizant.controller;

import com.cognizant.dto.DisplayDataDto;
import com.cognizant.dto.DriverDto;
import com.cognizant.entities.Driver;
import com.cognizant.response.ResponseHandler;
import com.cognizant.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping()
@CrossOrigin(origins = "http://localhost:3000")
public class DriverControl {

    @Autowired
    private DriverService driverService;

    //Add the profile
    //http://localhost:8080/addprofile
    @PostMapping("addprofile")
    public ResponseEntity<Object> addprofile(@Valid @RequestBody DriverDto driverdetail){
        DriverDto displaydata=driverService.addDriverData(driverdetail);

        if(displaydata!=null){
            return ResponseHandler.generateResponse("Driver saved successfully", HttpStatus.CREATED,displaydata);
        }
        else{
            return new ResponseEntity<>("Driver not Saved",HttpStatus.BAD_REQUEST);
        }
    }

    //View the profile by Id
    //http://localhost:8080/data/?id=SA00011
    @GetMapping("data/")
    public ResponseEntity<Object> viewProfileById(@RequestParam String id) {
        DriverDto displaydata = driverService.getDriverDataById(id);

        if (displaydata != null) {
            // Successful creation
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "message", "Driver found with Driver ID: " + displaydata.getId(),
                    "data", displaydata
            ));
        }
        // Driver save failure
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "message", "Driver not found with Id"
        ));
    }

    //View the profile by Email -- Not used in frontend
    //http://localhost:8080/?email=ram@gmail.com
    @GetMapping()
    public ResponseEntity<Object> viewProfileByEmail(@RequestParam(required = false) String email){
        DisplayDataDto displaydata= driverService.getDriverDataByEmail(email);

        if (displaydata != null) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "message", "Driver found with Driver Email: " + displaydata.getEmail(),
                    "data", displaydata
            ));
        }


        // Driver save failure
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "message", "Driver not found with Email"
        ));
    }

    //View the profile By AutoNo -- Not used in frontend
    //http://localhost:8080/autoNo/?autono=TN88F4089
    @GetMapping("autoNo/")
    public ResponseEntity<Object> viewProfileByAutoNo(@RequestParam(required = false) String autono){

        DriverDto displaydata=driverService.viewDriverData(autono);

        if(displaydata!=null){
            return ResponseHandler.generateResponse("Driver found with AutoNo:"+ autono, HttpStatus.FOUND,displaydata);
        }
        else{
            return new ResponseEntity<>("Driver not found with AutoNo:"+ autono,HttpStatus.BAD_REQUEST);
        }
    }

    //Edit the profile
    //http://localhost:8080/updateprofile/TN88F4089
    @PutMapping("updateprofile/{id}")
    public ResponseEntity<Object> editprofile(@PathVariable("id") String id, @Valid @RequestBody DriverDto driverdetail){
        DriverDto result=driverService.updateDriverData(id,driverdetail);
        if(result!=null){
            return new ResponseEntity<>(driverdetail.getName() +" - Your Details updated successfully ",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Driver Details not updated"+id,HttpStatus.BAD_REQUEST);
        }
    }

    //Available Auto's
    @GetMapping("avaiableautos")
    public ResponseEntity<Object> availableAutos(){
        List<Driver> avaiableAutos = driverService.availableAutos();

        if(avaiableAutos!=null){
            return new ResponseEntity<>(avaiableAutos,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("No Drivers are Active Now",HttpStatus.BAD_REQUEST);
        }
    }


}
