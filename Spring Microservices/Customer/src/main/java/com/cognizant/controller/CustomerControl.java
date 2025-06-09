package com.cognizant.controller;

import com.cognizant.dto.CustomerDto;
import com.cognizant.dto.DisplayDataDto;
import com.cognizant.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@Validated
@RequestMapping()
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerControl {

    private final CustomerService customerservice;

    public CustomerControl(CustomerService customerservice) {
        this.customerservice = customerservice;
    }
    //Add the profile
    //http://localhost:8081/addprofile
    @PostMapping("addprofile")
    public ResponseEntity<Object> addprofile(@Valid @RequestBody CustomerDto customerdetail){

        // Call the service layer to save customer data
        CustomerDto displayData = customerservice.addCustomerData(customerdetail);
        if (displayData != null) {
            // Successful creation
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Map.of(
                    "message", "Customer Signed successfully",
                    "data", displayData
            ));
        }

        // Customer save failure
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                "message", "Customer with EmailId Already Exists"
        ));
    }

    //View the profile by Email
    //http://localhost:8081/?email=akil@gmail.com
    @GetMapping()
    public ResponseEntity<Object> viewProfileByEmail(@RequestParam String email){
        DisplayDataDto displaydata= customerservice.getCustomerDataByEmail(email);

        if (displaydata != null) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "message", "Customer found with Email: " + displaydata.getEmail(),
                    "data", displaydata
            ));
        }

        // Customer save failure
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "message", "Customer not found with Email"
        ));
    }

    //View the profile by Id---- For Proxy
    //To check if customer exists before booking the seat
    //http://localhost:8081/SA00011
    @GetMapping("{id}")
    public DisplayDataDto getCustomerData(@PathVariable String id){
        DisplayDataDto displaydata= customerservice.getCustomerDataById(id);
        if (displaydata != null) {
            return displaydata;
        }
        return null;
    }

    //View the profile by Id --- For the Profile form
    //http://localhost:8081/data/?id=JAN01
    @GetMapping("data/")
    public ResponseEntity<Object> viewProfileById(@RequestParam String id){
        DisplayDataDto displaydata= customerservice.getCustomerDataById(id);
        if (displaydata != null) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "message", "Customer found" ,
                    "data", displaydata
            ));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "message", "Customer not found with Id"
        ));
    }

    //Edit the profile
    //http://localhost:8081/updateprofile/SA00012
    @PutMapping("updateprofile/{custid}")
    public ResponseEntity<Object> editprofile(@PathVariable("custid") String custid, @Valid @RequestBody CustomerDto customerdetail){
        CustomerDto result=customerservice.updateCustomerData(custid,customerdetail);
        if(result!=null){
            return new ResponseEntity<>(result.getName() + " Your Profile updated successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(" Your Profile not Found",HttpStatus.BAD_REQUEST);
        }
    }



}
