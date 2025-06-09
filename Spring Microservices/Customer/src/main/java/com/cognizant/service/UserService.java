package com.cognizant.service;

import com.cognizant.dto.AddUserDto;
import com.cognizant.dto.UpdateProfileRequest;
import com.cognizant.dto.UserDto;
import jakarta.validation.Valid;

import java.util.List;

public interface UserService {

    public UserDto getUserData( UserDto user);

    public List<String> getActiveAutos();

    public AddUserDto addUser(AddUserDto detail);

    public void updateProfile(UpdateProfileRequest updateData);


    public void logProfile(String id);
}
