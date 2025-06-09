package com.cognizant.controller;

import com.cognizant.dto.RidesDto;
import com.cognizant.dto.SeatBookingDto;
import com.cognizant.response.ResponseHandler;
import com.cognizant.service.SeatBookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping()
@CrossOrigin(origins = "http://localhost:3000")
public class SeatBookingControl {

    @Autowired
    private SeatBookingService seatService;


    //Add the profile --Done
    //http://localhost:8080/seatbooking/book
    @PostMapping("seatbooking/book")
    public ResponseEntity<Object> addprofile(@Valid @RequestBody SeatBookingDto seatdetail){

        SeatBookingDto displaydata=seatService.addseatData(seatdetail);

        if(displaydata!=null){
            return ResponseHandler.generateResponse(displaydata.getNo_of_seats()+" Seat booked successfully", HttpStatus.CREATED,displaydata);
        }
        else{
            return new ResponseEntity<>("seat not booked",HttpStatus.BAD_REQUEST);
        }
    }

    //View the by Booking Id -- Not used in frontend
    //http://localhost:8080/seatbooking/?bookid=BKQR87
    @GetMapping("seatbooking/")
    public ResponseEntity<Object> viewseatbyid(@RequestParam String bookid){
        SeatBookingDto displaydata=seatService.viewSeatbyId(bookid);

        if(displaydata!=null){
            return ResponseHandler.generateResponse("Seat found with Id:"+ displaydata.getBooking_id(), HttpStatus.FOUND,displaydata);
        }
        else{
            return new ResponseEntity<>("Seat not Found",HttpStatus.BAD_REQUEST);
        }
    }

    //View By Driver ID to check all the bookings he has
    //http://localhost:8080/ATRAM01
    @GetMapping("{driverid}")
    public ResponseEntity<Object> getBookingsByDriverId(@PathVariable String driverid) {
        List<RidesDto> bookings = seatService.viewById(driverid);
        bookings = bookings.stream()
                .sorted(Comparator.comparing(RidesDto::getDate))
                .collect(Collectors.toList());

        if(!bookings.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "message", "Rides are as follows",
                    "data", bookings
            ));
        }
        return new ResponseEntity<>("No Rides Available ",HttpStatus.BAD_REQUEST);
    }

    //Customer--View SeatBooking By custId to check all the bookings made
    //http://localhost:8080/viewbooking/?id=JAN001
    @GetMapping("/viewbooking/")
    public ResponseEntity<Object> getBookingsBycustid(@RequestParam(required = false) String id) {

        List<SeatBookingDto> bookings = seatService.viewbycustid(id);
        bookings = bookings.stream()
                .sorted(Comparator.comparing(SeatBookingDto::getDate))
                .collect(Collectors.toList());

        if(!bookings.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "message", "SeatBookings are as follows",
                    "data", bookings
            ));
        }
            return new ResponseEntity<>("Booking Details unavaiable with Customer Id:"+id,HttpStatus.BAD_REQUEST);
    }

    //Change the status to Completed
    //http://localhost:8080/status
    @GetMapping("/status")
    @Scheduled(fixedRate = 60000)
    public void updateBookingStatuses() {
        seatService.updateBookingStatuses();
    }

    //Delete
    //http://localhost:8080/seatbooking/?book_id=BKQR87
    @DeleteMapping("seatbooking/")
    public ResponseEntity<Object> deleteById(@RequestParam String booking_id){
        boolean result=seatService.deleteById(booking_id);

        if(result){
            return new ResponseEntity<>("Booking is Deleted Successfully" ,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Booking is Not Deleted" ,HttpStatus.BAD_REQUEST);
    }

    //Update
    //http://localhost:8080/seatbooking/?book_id=BKQR87
    @PatchMapping("seatbooking/")
    public ResponseEntity<Object> updateById(@RequestParam String booking_id,@RequestBody SeatBookingDto updateData){
        boolean result=seatService.updateById(booking_id,updateData);

        if(result){
            return new ResponseEntity<>("Booking is Updated Successfully" ,HttpStatus.OK);
        }
        return new ResponseEntity<>("Booking is Not Update" ,HttpStatus.BAD_REQUEST);
    }



}
