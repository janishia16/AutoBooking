package com.cognizant.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Entity
@Data
@Table(name="driver")
public class Driver {

    @Id
    @Column(name="id")
    private String id;

    @Column(name="autono")
    private String autono;

    private String license_no;

    @Column(name="name")
    private String name;

    @Column(name="phone")
    private String phone ;

    private String gender;

    @Column(name="email")
    private String email;

    private String password;

    private String confirmpassword;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
//    @ToString.Exclude
    @JsonIgnore
    private List<SeatBooking> seatBookings;

    public Driver() {
    }

    public Driver(String id, String autono, String license_no, String driver_name, String phone, String gender, String email) {
        this.id = id;
        this.autono = autono;
        this.license_no = license_no;
        this.name = driver_name;
        this.phone = phone;
        this.gender = gender;
        this.email = email;
    }

}
