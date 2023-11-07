package com.ssafy.newjibs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NewJibsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewJibsApplication.class, args);
	}

}
