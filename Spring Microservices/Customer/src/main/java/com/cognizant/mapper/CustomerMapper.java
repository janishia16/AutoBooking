package com.cognizant.mapper;

import com.cognizant.dto.CustomerDto;
import com.cognizant.dto.DisplayDataDto;
import com.cognizant.entities.Customer;
import com.cognizant.reposistory.CustomerReposistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    @Autowired
    private CustomerReposistory customerreposistory;

    //dto_to_database
    public CustomerDto dto_to_database(CustomerDto getdata,Customer setdata){
        setdata.setName(getdata.getName());
        setdata.setEmail(getdata.getEmail());   //It should be from signin email
        setdata.setPhone(getdata.getPhone());
        setdata.setGender(getdata.getGender());
        setdata.setPassword(getdata.getPassword());
        setdata.setConfirmpassword(getdata.getConfirmpassword());


        customerreposistory.save(setdata);
        return getdata;

    }


    // database_to_dto
    public CustomerDto database_to_dto(Customer getdata,CustomerDto setdata){
            setdata.setId(getdata.getId());
            setdata.setName(getdata.getName());
            setdata.setEmail(getdata.getEmail());
            setdata.setPhone(getdata.getPhone());
            setdata.setGender(getdata.getGender());
            setdata.setPassword(getdata.getPassword());
            setdata.setConfirmpassword(getdata.getConfirmpassword());

            return setdata;

    }

    //database_to_display
    public DisplayDataDto database_to_display(Customer getdata, DisplayDataDto setdata){
        setdata.setId(getdata.getId());
        setdata.setName(getdata.getName());
        setdata.setEmail(getdata.getEmail());
        setdata.setPhone(getdata.getPhone());
        setdata.setGender(getdata.getGender());
        setdata.setPassword(getdata.getPassword());

        return setdata;

    }

}
