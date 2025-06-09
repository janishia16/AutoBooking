package com.cognizant.service;

import com.cognizant.dto.RidesDto;
import com.cognizant.dto.SeatBookingDto;
import com.cognizant.entities.SeatBooking;

import java.util.List;
import java.util.Map;

public interface SeatBookingService {

    //Add seat
    public SeatBookingDto addseatData(SeatBookingDto seatdetail);

    //View by Booking Id
    public SeatBookingDto viewSeatbyId(String booking_id);

    //Customer--View Booking by driverId
    public List<RidesDto> viewById(String driverid);

    //View Bookings by SeatBooking
    public List<SeatBookingDto> viewbycustid(String custid);

    //Change Status to Active
    public void updateBookingStatuses();

    //Delete
    public boolean deleteById(String booking_id);

    //Update
    public boolean updateById(String booking_id, SeatBookingDto updateData);
}
