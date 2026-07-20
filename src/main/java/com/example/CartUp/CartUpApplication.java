package com.example.CartUp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class  CartUpApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartUpApplication.class, args);
	}

}
