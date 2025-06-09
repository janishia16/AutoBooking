package com.cognizant.serviceImplementation;

import com.cognizant.Feign.CustomerProxy;
import com.cognizant.dto.AddUserDto;
import com.cognizant.dto.DisplayDataDto;
import com.cognizant.dto.DriverDto;
import com.cognizant.dto.UpdateProfileRequest;
import com.cognizant.entities.Driver;
import com.cognizant.mapper.DriverMapper;
import com.cognizant.reposistory.DriverReposistory;
import com.cognizant.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cognizant.util.Generate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImplementation implements DriverService {

    @Autowired
    private DriverReposistory driverReposistory;

    @Autowired
    private DriverMapper mapper;

    @Autowired
    private CustomerProxy customerFeignClient;

    private Generate generator;


    //Add the profile
    public DriverDto addDriverData(DriverDto driverdetail){

        System.out.println("sadasd"+driverdetail.getName());

        Driver isuserpresent=driverReposistory.findByEmail(driverdetail.getEmail()).orElse(null);

        if(isuserpresent==null){

            Driver setdata=new Driver();
            driverdetail.setId(Generate.generateDriverId());
            setdata.setId(driverdetail.getId());


            //Adding the User
            AddUserDto adduser = new AddUserDto();
            adduser.setId(driverdetail.getId());
            adduser.setRole("DRIVER");
            adduser.setPassword(driverdetail.getPassword());
            adduser.setEmail(driverdetail.getEmail());
            customerFeignClient.addUser(adduser);


            DriverDto data= mapper.dto_to_database(driverdetail,setdata);
            return data;
        }
        return null;

    }


    //View the profile By Id
    public DriverDto getDriverDataById(String id){
        Driver getdata=driverReposistory.findById(id).orElse(null);
        DriverDto setdata = new DriverDto();
        if(getdata!=null) {
            return mapper.database_to_driverDisplay(getdata,setdata);
        }
        return null;
    }

    //View the profile By Auto No -- Not used in frontend
    public DriverDto viewDriverData(String autono){

        Driver setdata=driverReposistory.findByAutono(autono).orElse(null);

        if(setdata!=null){
            DriverDto driverdetail=new DriverDto();
            DriverDto data= mapper.database_to_dto(setdata,driverdetail);
            return data;
        }
        return null;

    }

    //View the profile by Email -- Not used in frontend
    public DisplayDataDto getDriverDataByEmail(String email){
        Driver getdata=driverReposistory.findByEmail(email).orElse(null);

        DisplayDataDto setdata = new DisplayDataDto();
        if(getdata!=null) {
            return mapper.database_to_display(getdata,setdata);
        }
        return null;
    }

    //Update the profile
    public DriverDto updateDriverData(String id,DriverDto driverdetail){

        Optional<Driver> optionalUser=driverReposistory.findById(id);
        if(optionalUser.isPresent()) {
            Driver driver = optionalUser.get();

            //Update User Table
            UpdateProfileRequest updateData=new UpdateProfileRequest(driverdetail.getPassword(),driverdetail.getEmail());
            customerFeignClient.updateProfile(updateData);

            return mapper.dto_to_database(driverdetail,driver);

        }
        return null;
    }

    //Available Auto's
    public List<Driver> availableAutos(){

        List<Driver> avaiableAutos = new ArrayList<>();
        List<Driver> Autos = driverReposistory.findAll();
        List<String> avaibleAutos = customerFeignClient.getActiveAutos();


        if(!Autos.isEmpty()){
            Autos.stream()
                .filter( auto -> avaibleAutos.contains(auto.getId()))
                .forEach(auto -> {
                    avaiableAutos.add(new Driver(auto.getId(),auto.getAutono(),auto.getLicense_no(),auto.getName(),auto.getPhone(),auto.getGender(),auto.getEmail()));
                });

        }
        return avaiableAutos;

    }






}
