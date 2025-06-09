package com.cognizant.serviceImplementation;

import com.cognizant.dto.PaymentDto;
import com.cognizant.entities.Customer;
import com.cognizant.entities.Payment;
import com.cognizant.mapper.PaymentMapper;
import com.cognizant.reposistory.PaymentReposistory;
import com.cognizant.service.CustomerService;
import com.cognizant.service.PaymentService;
import com.cognizant.utility.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImplementation implements PaymentService {

    @Autowired
    private PaymentMapper mapper;

    @Autowired
    private CustomerService customerservice;

    @Autowired
    private PaymentReposistory paymentreposistory;

    private Generator geneartor;

    //Add payment
    public PaymentDto addPayment(String custid,  PaymentDto paymentdetail){
        Optional<Customer> isuserpresent=customerservice.findid(custid);

        if(isuserpresent.isPresent()){
            String paymentid=geneartor.generatePaymentId();

            Payment newpayment=new Payment();

            //Set Customer id
            newpayment.setCustid(custid);
            paymentdetail.setCustid(custid);

            //Set Date
            newpayment.setDate(LocalDate.now());
            paymentdetail.setDate(LocalDate.now());

            newpayment.setPayment_id(paymentid);

            PaymentDto dtoObject=mapper.dto_to_database(paymentdetail,newpayment); //DTO to database

            if (dtoObject!=null)
                return dtoObject;
            else
                return null;
        }
        return null;
    }

    //View all payments(custid)
    public List<PaymentDto> viewallpayments(String custid){
        Optional<Customer> isuserpresent=customerservice.findid(custid);
        if(isuserpresent.isPresent()){
            Iterable<Payment> alldata =paymentreposistory.findAllByCustid(custid);
            return mapper.list_to_dto(alldata);
        }
        return null;
    }

    //View payments by Payment_ID
    public PaymentDto viewbypaymentid(String custid, String paymentid){
        Optional<Customer> isuserpresent=customerservice.findid(custid);
        if(isuserpresent.isPresent()){
            Optional<Payment> optionaldata =paymentreposistory.findById(paymentid);
            Payment getdata=optionaldata.get();

            PaymentDto setdtodata=new PaymentDto();
            setdtodata.setPayment_id(paymentid);

            PaymentDto payment=mapper.database_to_dto(getdata,setdtodata);
            if (payment!=null)
                return payment;
            else
                return null;
        }
        return null;
    }

    //View payments by Date
    public List<PaymentDto> viewbydate(String custid, String date){
        Optional<Customer> isuserpresent=customerservice.findid(custid);
        if(isuserpresent.isPresent()){
            LocalDate Date = LocalDate.parse(date);
            Iterable<Payment> alldata =paymentreposistory.findAllByDate(Date);
            return mapper.list_to_dto(alldata);
        }
        return null;
    }
}
