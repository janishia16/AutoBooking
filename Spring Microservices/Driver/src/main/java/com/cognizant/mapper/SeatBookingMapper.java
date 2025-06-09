package com.cognizant.mapper;


import com.cognizant.Feign.CustomerProxy;
import com.cognizant.dto.DisplayDataDto;
import com.cognizant.dto.RidesDto;
import com.cognizant.dto.SeatBookingDto;
import com.cognizant.entities.SeatBooking;
import com.cognizant.reposistory.DriverReposistory;
import com.cognizant.reposistory.SeatBookingReposistory;
import com.cognizant.util.Generate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SeatBookingMapper {

    @Autowired
    private SeatBookingReposistory seatreposistory;

    @Autowired
    private Generate generate;

    @Autowired
    private DriverReposistory driverReposistory;

    @Autowired
    private CustomerProxy customerProxy;

    //dto_to_database
    public SeatBookingDto dto_to_database(SeatBookingDto getdtodata, SeatBooking setdata){

        //Getting Auto NO through Driver ID
        String autoNo= driverReposistory.findAutonoByDriverId(getdtodata.getDriver().getId());

        setdata.setAutono(autoNo);
        getdtodata.setAutono(autoNo);

        setdata.setBooking_id(getdtodata.getBooking_id());

        setdata.setNo_of_seats(getdtodata.getNo_of_seats());
        setdata.setDate(getdtodata.getDate());
        setdata.setTime(getdtodata.getTime());
        setdata.setCustid(getdtodata.getId());
        setdata.setStatus(getdtodata.getStatus());

        seatreposistory.save(setdata);
        return getdtodata;
    }

    //database_to_dto
    public SeatBookingDto database_to_dto(SeatBooking getdata, SeatBookingDto setdtodata){

        setdtodata.setBooking_id(getdata.getBooking_id());
        setdtodata.setNo_of_seats(getdata.getNo_of_seats());
        setdtodata.setDriver(getdata.getDriver());
        setdtodata.setAutono(getdata.getAutono());
        setdtodata.setDate(getdata.getDate());
        setdtodata.setTime(getdata.getTime());
        setdtodata.setId(getdata.getCustid());

        setdtodata.setStatus(getdata.getStatus());
        return setdtodata;
    }

    //list_to_dto
    public List<SeatBookingDto> list_to_dto(Iterable<SeatBooking> alldata){
        List<SeatBookingDto>  allbookings = new ArrayList<>();

        alldata.forEach( data ->{
            SeatBookingDto object = new SeatBookingDto(); //Add each object to list
            object.setBooking_id(data.getBooking_id());
            object.setNo_of_seats(data.getNo_of_seats());
            object.setTime(data.getTime());
            object.setDate(data.getDate());
            object.setId(data.getCustid());
            object.setAutono(data.getAutono());
            object.setDriver(data.getDriver());
            object.setStatus(data.getStatus());

            allbookings.add(object);

        });

        return allbookings;
    }

    //updatedto_to_database
    public SeatBookingDto updatedto_to_database(SeatBookingDto getdtodata, SeatBooking setdata){

        setdata.setNo_of_seats(getdtodata.getNo_of_seats());
        setdata.setDate(getdtodata.getDate());
        setdata.setTime(getdtodata.getTime());
        seatreposistory.save(setdata);
        return getdtodata;
    }

    //list_to_dto
    public List<RidesDto> list_to_ridedata(Iterable<SeatBooking> alldata){
        List<RidesDto>  allbookings = new ArrayList<>();

        alldata.forEach( data ->{
            RidesDto object = new RidesDto(); //Add each object to list

            //Customer Data
            Optional<DisplayDataDto> customerDetail =  customerProxy.getCustomerData(data.getCustid());
            object.setBooking_id(data.getBooking_id());
            object.setCustomername(customerDetail.get().getName());
            object.setPhone(customerDetail.get().getPhone());
            object.setNo_of_seats(data.getNo_of_seats());
            object.setTime(data.getTime());
            object.setDate(data.getDate());
            object.setStatus(data.getStatus());

            allbookings.add(object);

        });

        return allbookings;
    }
}
