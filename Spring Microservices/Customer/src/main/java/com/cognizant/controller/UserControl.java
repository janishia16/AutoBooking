package com.cognizant.controller;


import com.cognizant.dto.AddUserDto;
import com.cognizant.dto.UpdateProfileRequest;
import com.cognizant.dto.UserDto;
import com.cognizant.exception.InvalidPasswordException;
import com.cognizant.exception.UserNotFoundException;
import com.cognizant.service.UserService;

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
public class UserControl {

    @Autowired
    private UserService userService;

    //Check the profile----SignIn
    //http://localhost:8081/userlogin
    @PostMapping("/userlogin")
    public ResponseEntity<Object> getUserData(@RequestBody @Valid UserDto loginData){
        try {

            UserDto userData = userService.getUserData(loginData);
            return ResponseEntity.status(HttpStatus.FOUND).body(Map.of(
                    "message", "User Found",
                    "data", userData
            ));
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "message", e.getMessage()
            ));
        } catch (InvalidPasswordException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "message", e.getMessage()
            ));
        }
    }

    //View the Auto's
    //Only Assign Auto's that are active
    @GetMapping("activeAutos")
    public List<String> getActiveAutos(){
        List<String> Autos = userService.getActiveAutos();
        return Autos;

    }

    //Add the profile---SignUp
    //http://localhost:8081/adduser
    @PostMapping("/adduser")
    public ResponseEntity<Object> addUser(@RequestBody @Valid AddUserDto data){

        AddUserDto userData = userService.addUser(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "User Added",
                "data", userData
        ));
    }

    //Update the profile-- For Driver Proxy
    @PutMapping("/updateuser")
    public void updateProfile(@RequestBody UpdateProfileRequest updateData){
        userService.updateProfile(updateData);
    }

    //Logout the profile
    @GetMapping("/logout/{id}")
    public ResponseEntity<Object> logProfile(@PathVariable String id){
        userService.logProfile(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", "User Logged Off"
        ));
    }

}
