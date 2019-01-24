package com.temperatures;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class TemperaturesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemperaturesApplication.class, args);
	}



}

