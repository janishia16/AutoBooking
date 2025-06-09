package com.cognizant.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Locale;

@Entity
@Table(name="payment")
@Configuration
@Data
public class Payment {

    @Id
    @Column(name="payment_id")
    private String payment_id;

    private String upiid;

    @Column(name="phone")
    private String phone;

    @Column(name="amount")
    private int amount;

    private LocalDate date;

    private String custid;



}
