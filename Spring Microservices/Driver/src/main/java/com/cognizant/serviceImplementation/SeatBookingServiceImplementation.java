package com.cognizant.serviceImplementation;

import com.cognizant.Feign.CustomerProxy;
import com.cognizant.dto.DisplayDataDto;
import com.cognizant.dto.RidesDto;
import com.cognizant.dto.SeatBookingDto;
import com.cognizant.entities.Driver;
import com.cognizant.entities.SeatBooking;
import com.cognizant.mapper.SeatBookingMapper;
import com.cognizant.reposistory.DriverReposistory;
import com.cognizant.reposistory.SeatBookingReposistory;
import com.cognizant.service.DriverService;
import com.cognizant.service.SeatBookingService;
import com.cognizant.util.Generate;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class SeatBookingServiceImplementation implements SeatBookingService {


    @Autowired
    private SeatBookingReposistory seatReposistory;

    @Autowired
    private DriverReposistory driverReposistory;

    @Autowired
    private DriverService driverService;

    @Autowired
    private SeatBookingMapper mapper;

    @Autowired
    private CustomerProxy customerFeignClient;

    @Autowired
    private Generate generate;

    //Add the Seat
    public SeatBookingDto addseatData(SeatBookingDto seatdetail){

        //User is present before booking
        Optional<DisplayDataDto> isuserpresent = customerFeignClient.getCustomerData(seatdetail.getId());
        if(!isuserpresent.isEmpty()){
            SeatBooking setdata=new SeatBooking();

            //Genearte a AutoNumber
            Driver availableAuto = generate.getRandomAutoNumber();
            seatdetail.setDriver(availableAuto);
            setdata.setDriver(availableAuto);

            //Generate Booking Id
            seatdetail.setBooking_id(generate.generateBookingId());
            seatdetail.setStatus("Active");

            SeatBookingDto data= mapper.dto_to_database(seatdetail,setdata);

            return data;
        }
        return null;

    }

    //View the Seat by Booking Id -- Not used in frontend
    public SeatBookingDto viewSeatbyId(String booking_id){

        SeatBooking ispresent=seatReposistory.findById(booking_id).orElse(null);

        if(ispresent!=null) {
            SeatBookingDto seatdetail = new SeatBookingDto();
            SeatBookingDto data = mapper.database_to_dto(ispresent, seatdetail);
            return data;
        }
        return null;

    }

    //Driver--View Booking by DriverId--------for driver to see how many rides are there
    public List<RidesDto> viewById(String driverid){
        String AutoNo = driverReposistory.findAutonoByDriverId(driverid);

        if(!AutoNo.isEmpty()){
            Iterable<SeatBooking> Data= seatReposistory.findAllByAutono(AutoNo);
            return mapper.list_to_ridedata(Data);
        }
        return null;

    }

    //Customer--View Booking by Custid --------for customer to see how many rides are there
    public List<SeatBookingDto> viewbycustid(String custid){
        Iterable<SeatBooking> Data= seatReposistory.findAllByCustid(custid);
        return mapper.list_to_dto(Data);
    }

    //Change the status -- Completed
    public void updateBookingStatuses() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalTime thresholdTime = currentTime.minusMinutes(30).toLocalTime().truncatedTo(ChronoUnit.SECONDS);

        List<SeatBooking> bookings = seatReposistory.findBookingsToUpdate();

        for (SeatBooking booking : bookings) {
            booking.setStatus("Completed");
            seatReposistory.save(booking);
        }
    }

    //Delete
    public boolean deleteById(String booking_id) {
        try {
            seatReposistory.deleteById(booking_id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    //Update
    public boolean updateById(String booking_id, SeatBookingDto updateData){

        Optional<DisplayDataDto> isuserpresent = customerFeignClient.getCustomerData(updateData.getId());
        Optional<SeatBooking> isBookingIdPresent = seatReposistory.findById(booking_id);

        if(!isuserpresent.isEmpty() && isBookingIdPresent.isPresent()){

            mapper.updatedto_to_database(updateData,isBookingIdPresent.get());
            return true;
        }
        return false;
    }
}
