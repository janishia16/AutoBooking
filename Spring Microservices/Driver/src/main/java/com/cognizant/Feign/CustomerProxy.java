package com.cognizant.Feign;

import com.cognizant.dto.AddUserDto;
import com.cognizant.dto.DisplayDataDto;
import com.cognizant.dto.UpdateProfileRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name="Customer" , url="http://localhost:8081")
@Component
public interface CustomerProxy {

    //View the profile by Id
    @GetMapping("{id}")
    Optional<DisplayDataDto> getCustomerData(@PathVariable("id") String id);

    //View the Auto's
    @GetMapping("activeAutos")
    List<String> getActiveAutos();

    //User Add
    @PostMapping("/adduser")
    AddUserDto addUser(AddUserDto detail);

    //Update User
    @PutMapping("/updateuser")
    void updateProfile(UpdateProfileRequest updateData);

}
