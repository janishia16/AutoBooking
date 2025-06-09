package com.cognizant.controller;


import com.cognizant.dto.CustomerDto;
import com.cognizant.dto.PaymentDto;
import com.cognizant.response.ResponseHandler;
import com.cognizant.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping()
public class PaymentControl {

    @Autowired
    private PaymentService paymentservice;

    //Add the payment
    //http://localhost:8081/SA00011/payment/addpayment
    @PostMapping("{custid}/payment/addpayment")
    public ResponseEntity<?> addpayment(@PathVariable("custid") String custid , @Valid @RequestBody PaymentDto paymentdetail){

        PaymentDto displaydata=paymentservice.addPayment(custid,paymentdetail);

        if(displaydata!=null){
            String id=displaydata.getPayment_id();
            return ResponseHandler.generateResponse("Payment completed successfully with Payment ID : "+id, HttpStatus.CREATED,displaydata);
        }
        else{
            return new ResponseEntity<>("Payment Unsuccessfull",HttpStatus.BAD_REQUEST);
        }
    }

    //View all the payment
    //http://localhost:8081/SA00011/payment/viewpayment
    @GetMapping("{custid}/payment/viewpayment")
    public ResponseEntity<Object> viewpayment(@PathVariable("custid") String custid){
        List<PaymentDto> displaydata=paymentservice.viewallpayments(custid);
        if(!displaydata.isEmpty())
            return new ResponseEntity<>(displaydata,HttpStatus.OK);
        else
            return new ResponseEntity<>("Payment Details unavaiable",HttpStatus.BAD_REQUEST);
    }

    //View the payment by id
    //http://localhost:8081/SA00011/payment/pay?paymentid=020f1bc0-d70a-432a-82ca-f714cd20dfae
    @GetMapping("{custid}/payment/pay")
    public ResponseEntity<Object> viewbyid(@PathVariable("custid") String custid,@RequestParam(required = false) String paymentid){
        PaymentDto displaydata=paymentservice.viewbypaymentid(custid,paymentid);
        if(displaydata!=null)
            return new ResponseEntity<>(displaydata,HttpStatus.OK);
        return new ResponseEntity<>("Payment Details unavaiable with payment Id:"+displaydata.getPayment_id(),HttpStatus.BAD_REQUEST);
    }

    //View the payment by Date
    //http://localhost:8081/SA00011/payment/paydate?date=2024-12-03
    @GetMapping("{custid}/payment/paydate")
    public ResponseEntity<Object> viewbydate(@PathVariable("custid") String custid,@RequestParam(required = false) String date){
        List<PaymentDto> displaydata=paymentservice.viewbydate(custid,date);
        if(!displaydata.isEmpty())
            return new ResponseEntity<>(displaydata,HttpStatus.OK);
        return new ResponseEntity<>("Payment Details unavaiable with Date:"+date,HttpStatus.BAD_REQUEST);
    }


}
