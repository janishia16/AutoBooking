package com.cognizant.mapper;

import com.cognizant.dto.DisplayDataDto;
import com.cognizant.dto.DriverDto;
import com.cognizant.entities.Driver;
import com.cognizant.reposistory.DriverReposistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class DriverMapper {

    @Autowired
    private DriverReposistory driverReposistory;

    //dto_to_database
    public DriverDto dto_to_database(DriverDto getdtodata, Driver setdata){

        setdata.setAutono(getdtodata.getAutono());
        setdata.setLicense_no(getdtodata.getLicense_no());
        setdata.setName(getdtodata.getName());
        setdata.setPhone(getdtodata.getPhone());
        setdata.setGender(getdtodata.getGender());
        setdata.setEmail(getdtodata.getEmail());
        setdata.setPassword(getdtodata.getPassword());
        setdata.setConfirmpassword(getdtodata.getConfirmpassword());

        driverReposistory.save(setdata);
        return getdtodata;
    }

    //database_to_dto
    public DriverDto database_to_dto(Driver getdata, DriverDto setdtodata){

        setdtodata.setAutono(getdata.getAutono());
        setdtodata.setLicense_no(getdata.getLicense_no());
        setdtodata.setName(getdata.getName());
        setdtodata.setId(getdata.getId());
        setdtodata.setPhone(getdata.getPhone());
        setdtodata.setEmail(getdata.getEmail());
        setdtodata.setGender(getdata.getGender());

        return setdtodata;
    }

    //update
    public DriverDto update_to_database(DriverDto getdtodata, Driver setdata){
            for (Field field : DriverDto.class.getDeclaredFields()) {

                //field=private java.lang.String com.cognizant.dto.DriverDto.auto_no
                field.setAccessible(true);
                try {
                    Object value = field.get(getdtodata); //check if data field is there in given data
                    if (value != null) {
                        Field userField = Driver.class.getDeclaredField(field.getName());
                        //field.getName() = phone & email
                        userField.setAccessible(true);
                        userField.set(setdata, value);
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    // Handle the exception
                    e.printStackTrace();
                }
            }
            driverReposistory.save(setdata);
            return getdtodata;

    }

    //database_to_display
    public DisplayDataDto database_to_display(Driver getdata, DisplayDataDto setdata){
        setdata.setId(getdata.getId());
        setdata.setName(getdata.getName());
        setdata.setEmail(getdata.getEmail());
        setdata.setPhone(getdata.getPhone());
        setdata.setGender(getdata.getGender());

        return setdata;

    }

    //database_to_driverDisplay
    public DriverDto database_to_driverDisplay(Driver getdata, DriverDto setdata){
        setdata.setId(getdata.getId());
        setdata.setName(getdata.getName());
        setdata.setEmail(getdata.getEmail());
        setdata.setPhone(getdata.getPhone());
        setdata.setGender(getdata.getGender());
        setdata.setLicense_no(getdata.getLicense_no());
        setdata.setAutono(getdata.getAutono());
        setdata.setPassword(getdata.getPassword());
        setdata.setConfirmpassword(getdata.getConfirmpassword());

        return setdata;

    }

}
