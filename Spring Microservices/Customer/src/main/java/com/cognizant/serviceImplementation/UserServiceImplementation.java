package com.cognizant.serviceImplementation;

import com.cognizant.dto.AddUserDto;
import com.cognizant.dto.CustomerDto;
import com.cognizant.dto.UpdateProfileRequest;
import com.cognizant.dto.UserDto;
import com.cognizant.entities.Users;
import com.cognizant.reposistory.UserReposistory;
import com.cognizant.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cognizant.exception.UserNotFoundException;
import com.cognizant.exception.InvalidPasswordException;
import com.cognizant.utility.JwtUtil;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static java.lang.Boolean.FALSE;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserReposistory userReposistory;


    //Login the user
    public UserDto getUserData(UserDto user) throws UserNotFoundException, InvalidPasswordException {
        Users existingUser = userReposistory.findById(user.getEmail()).orElse(null);
        if (existingUser != null) {
            UserDto userDto = new UserDto();
            userDto.setEmail(existingUser.getEmail());
            userDto.setPassword(existingUser.getPassword());
            userDto.setLogin(true);
            userDto.setId(existingUser.getUserid());

            existingUser.setLogin(true);
            userReposistory.save(existingUser);

            //Save the user in User Table
            String token = jwtUtil.generateToken(existingUser.getEmail());

            userDto.setToken(token);


            if (existingUser.getPassword().equals(user.getPassword())) {
                return userDto;
            } else {
                throw new InvalidPasswordException("Password is wrong");
            }
        }
        throw new UserNotFoundException("User not found");
    }

    //View the Auto's
    //Only Assign Auto's that are active
    public List<String> getActiveAutos(){
        return userReposistory.findAllActiveDrivers();

    }

    //Add the user Data --- SignUp
    public AddUserDto addUser(AddUserDto detail){
        Users users = new Users();
        users.setEmail(detail.getEmail());
        users.setPassword(detail.getPassword());
        users.setUserid(detail.getId());
        users.setRole(detail.getRole());

        userReposistory.save(users);
        return detail;
    }

    //Update profile
    public void updateProfile(UpdateProfileRequest updateData){
        Users users = userReposistory.findById(updateData.getEmail()).orElse(null);
        users.setPassword(updateData.getPassword());
        userReposistory.save(users);
    }

    //Logout
    public void logProfile(String id){
        Users users = userReposistory.findByUserid(id);
        users.setLogin(FALSE);
        userReposistory.save(users);
    }

}
