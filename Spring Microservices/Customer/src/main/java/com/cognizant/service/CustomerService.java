package com.cognizant.service;

import com.cognizant.dto.CustomerDto;
import com.cognizant.dto.DisplayDataDto;
import com.cognizant.entities.Customer;

import java.util.Optional;

public interface CustomerService {


    //Add the profile
    public CustomerDto addCustomerData(CustomerDto customerdetail);

    //View the profile By email
    public DisplayDataDto getCustomerDataByEmail(String email);

    //View the profile By Id
    public DisplayDataDto getCustomerDataById(String id);

    //Update the profile
    public CustomerDto updateCustomerData(String custid,CustomerDto customerdetail);

    //Find id
    public Optional<Customer> findid(String custid);
}
