package com.example.spring_event_listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class SpringEventListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEventListenerApplication.class, args);
	}

}
