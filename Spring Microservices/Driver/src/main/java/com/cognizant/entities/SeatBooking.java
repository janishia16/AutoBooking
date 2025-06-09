package com.cognizant.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity

@Configuration
@Getter
@Setter

@Table(name="seatbooking")
public class SeatBooking {

    @Id
    @Column(name="booking_id")
    private String booking_id;

    @Column(name="no_of_seats")
    private int no_of_seats;

    @Column(name="date")
    private LocalDate date;

    @Column(name="time")
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name="driver", referencedColumnName = "id")
    @JsonIgnore // it repeats so we ignore
    private Driver driver;


    @Column(name="autono")
    private String autono;

    @Column(name="id")
    private String custid; //Customer

    @Column(name="status")
    private String status;


    @Override
    public String toString() {
        return "SeatBooking{" +
                "booking_id=" + booking_id +
                ", no_of_seats=" + no_of_seats +
                ", date=" + date +
                ", time=" + time +
                ", driver=" + driver +
                ", id='" + custid + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
