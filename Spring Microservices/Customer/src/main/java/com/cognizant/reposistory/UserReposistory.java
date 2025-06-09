package com.cognizant.reposistory;

import com.cognizant.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserReposistory extends JpaRepository<Users,String> {

    @Query("SELECT u.userid FROM Users u WHERE u.role = 'DRIVER' AND u.isLogin = true")
    List<String> findAllActiveDrivers();


    Users findByUserid(String userid);


}
