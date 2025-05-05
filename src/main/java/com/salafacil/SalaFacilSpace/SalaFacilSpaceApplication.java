package com.salafacil.SalaFacilSpace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SalaFacilSpaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalaFacilSpaceApplication.class, args);
	}

}
