package com.example.greenery;

import com.example.greenery.repo.AuthDataRepo;
import com.example.greenery.service.AuthDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

@SpringBootApplication(scanBasePackages = {"com.example.greenery.repo", "com.example.greenery.controllers",
		"com.example.greenery.service", "com.example.greenery.model", "com.example.greenery.config",
		"com.example.greenery.exceptions"})
public class GreeneryApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreeneryApplication.class, args);
	}

}
