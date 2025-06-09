package com.cognizant.service;

import com.cognizant.dto.PaymentDto;

import java.util.List;

public interface PaymentService {

    //Add payment
    public PaymentDto addPayment(String custid, PaymentDto paymentdetail);

    //View Payment
    public List<PaymentDto> viewallpayments(String id);

    //View Payment by paymentid
    public PaymentDto viewbypaymentid(String custid, String paymentid);

    //View Payment by Date
    public List<PaymentDto> viewbydate(String custid, String date);
}
