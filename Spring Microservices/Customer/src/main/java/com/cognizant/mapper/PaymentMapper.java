package com.cognizant.mapper;

import com.cognizant.dto.CustomerDto;
import com.cognizant.dto.PaymentDto;
import com.cognizant.entities.Customer;
import com.cognizant.entities.Payment;
import com.cognizant.reposistory.PaymentReposistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PaymentMapper {

    @Autowired
    private PaymentReposistory paymentreposistory;

    //dto_to_database
    public PaymentDto dto_to_database(PaymentDto getdtodata,Payment setdata){

        getdtodata.setPayment_id(setdata.getPayment_id());
        setdata.setUpiid(getdtodata.getUpiid());
        setdata.setPhone(getdtodata.getPhone());
        setdata.setAmount(getdtodata.getAmount());
        setdata.setDate(getdtodata.getDate());

        paymentreposistory.save(setdata);
        return getdtodata;
    }

    //database_to_dto
    public PaymentDto database_to_dto(Payment getdata, PaymentDto setdtodata){
        setdtodata.setUpiid(getdata.getUpiid());
        setdtodata.setPhone(getdata.getPhone());
        setdtodata.setAmount(getdata.getAmount());
        setdtodata.setDate(getdata.getDate());
        setdtodata.setCustid(getdata.getCustid());
        return setdtodata;
    }

    //list_to_dto
    public List<PaymentDto> list_to_dto(Iterable<Payment> alldata){

        List<PaymentDto>  allpayments = new ArrayList<>();

        alldata.forEach( data ->{
            PaymentDto paymentobject = new PaymentDto(); //Add each object to list
            paymentobject.setPayment_id(data.getPayment_id());
            paymentobject.setUpiid(data.getUpiid());
            paymentobject.setPhone(data.getPhone());
            paymentobject.setAmount(data.getAmount());
            paymentobject.setDate(data.getDate());
            paymentobject.setCustid(data.getCustid());

            allpayments.add(paymentobject);
            System.out.println(allpayments);

        });

        return allpayments;
    }



}
