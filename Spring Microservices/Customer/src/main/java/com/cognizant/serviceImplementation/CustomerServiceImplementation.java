package com.cognizant.serviceImplementation;

import com.cognizant.dto.CustomerDto;
import com.cognizant.dto.DisplayDataDto;
import com.cognizant.entities.Customer;
import com.cognizant.entities.Users;
import com.cognizant.mapper.CustomerMapper;
import com.cognizant.reposistory.CustomerReposistory;
import com.cognizant.reposistory.UserReposistory;
import com.cognizant.service.CustomerService;
import com.cognizant.utility.Generator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImplementation implements CustomerService {

    private final CustomerReposistory customerreposistory;
    private final CustomerMapper mapper;
    private final  UserReposistory userReposistory;
    private Generator generator;

    public CustomerServiceImplementation(CustomerReposistory customerreposistory, CustomerMapper mapper,UserReposistory userReposistory) {
        this.customerreposistory = customerreposistory;
        this.mapper = mapper;
        this.userReposistory = userReposistory;
    }


    //Add the profile
   public CustomerDto addCustomerData(CustomerDto customerdetail) {
       String custid = generator.generateCustomerId(); //It should be done in user microservice

       Customer isuserpresent = customerreposistory.findByEmail(customerdetail.getEmail()).orElse(null); // Check in user microservice

       if (isuserpresent == null) {
           Customer setdata = new Customer();
           setdata.setId(custid);
           customerdetail.setId(setdata.getId());


           Users users = new Users();
           users.setEmail(customerdetail.getEmail());
           users.setPassword(customerdetail.getPassword());
           users.setUserid(customerdetail.getId());
           users.setRole("USER");

           userReposistory.save(users);


           return mapper.dto_to_database(customerdetail, setdata);

       }
       return null;
   }


    //View the profile by Email
    public DisplayDataDto getCustomerDataByEmail(String email){

        Customer getdata=customerreposistory.findByEmail(email).orElse(null);

        DisplayDataDto setdata = new DisplayDataDto();
        if(getdata!=null) {
            return mapper.database_to_display(getdata,setdata);
        }
        return null;
    }

    //View the profile By Id
    public DisplayDataDto getCustomerDataById(String id){
        Customer getdata=customerreposistory.findById(id).orElse(null);

        DisplayDataDto setdata = new DisplayDataDto();
        if(getdata!=null) {
            return mapper.database_to_display(getdata,setdata);
        }
        return null;
    }

    //Update the profile
    public CustomerDto updateCustomerData(String custid,CustomerDto customerdetail){

        Customer setdata=customerreposistory.findById(custid).orElse(null);
        if(setdata!=null) {
            Users userData = userReposistory.findById(customerdetail.getEmail()).orElse(null);
            userData.setPassword(customerdetail.getPassword());
            return mapper.dto_to_database(customerdetail,setdata);

        }
        return null;
    }

    //Find the Customer
    //Used in Payment class
    public Optional<Customer> findid(String custid){
         Optional<Customer> id= customerreposistory.findById(custid);
         return id;
    }




}


