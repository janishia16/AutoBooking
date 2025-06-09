package com.cognizant.reposistory;

import com.cognizant.entities.Driver;
import com.cognizant.entities.SeatBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SeatBookingReposistory extends JpaRepository<SeatBooking,String> {


    @Query("SELECT s FROM SeatBooking s WHERE s.autono = :autono")
    List<SeatBooking> findAllByAutono(@Param("autono") String autono);

//    Optional<SeatBooking> findByCustid(String custid);
    @Query("SELECT d FROM SeatBooking d WHERE d.id = :id")
    Optional<SeatBooking> findByCustomId(@Param("id") String id);

    List<SeatBooking> findAllByCustid(String custid);
//    @Query("SELECT s FROM SeatBooking s WHERE s.custid = :custid")
//    List<SeatBooking> findAllByCustid(@Param("custid") String custid);


    @Query("SELECT b FROM SeatBooking b WHERE b.status = 'Active' " +
            "AND b.date < CURRENT_DATE ")
    List<SeatBooking> findBookingsToUpdate();


    @Query("SELECT b FROM SeatBooking b WHERE b.status = 'Active' " +
            "AND b.date < CURRENT_DATE")
    List<SeatBooking> findActiveBookingsBeforeToday();

}
