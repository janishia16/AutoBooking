package com.cognizant.reposistory;

import com.cognizant.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverReposistory extends JpaRepository<Driver,String> {

    Optional<Driver> findByEmail(String email);

    Optional<Driver> findByAutono(String autono);

    @Query("SELECT d.autono FROM Driver d WHERE d.id = :id")
    String findAutonoByDriverId(@Param("id") String id);

}
