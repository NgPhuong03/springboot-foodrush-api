package com.foodrush.mobile_api;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MobileApiApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	};

	public static void main(String[] args) {
		SpringApplication.run(MobileApiApplication.class, args);
	}

}
