package com.cognizant.reposistory;

import com.cognizant.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerReposistory extends JpaRepository<Customer,String> {

    Optional<Customer> findByEmail(String email);


}
