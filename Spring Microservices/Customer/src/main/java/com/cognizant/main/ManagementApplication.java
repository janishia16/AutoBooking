package com.cognizant.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.cognizant.entities")
@EnableJpaRepositories(basePackages = "com.cognizant.reposistory")
@SpringBootApplication(scanBasePackages= "com.cognizant.*")
@EnableFeignClients(basePackages = "com.cognizant.service")
@EnableAspectJAutoProxy
public class ManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementApplication.class, args);
	}

}
