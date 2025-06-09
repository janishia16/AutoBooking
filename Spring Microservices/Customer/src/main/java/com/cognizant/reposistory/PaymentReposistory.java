package com.cognizant.reposistory;

import com.cognizant.entities.Customer;
import com.cognizant.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentReposistory extends JpaRepository<Payment,String> {

    //Customer Id
    Iterable<Payment> findAllByCustid(String id);

    //Date
    Iterable<Payment> findAllByDate(LocalDate date);
}
