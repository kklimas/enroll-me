package com.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}
// todo enable user to set preferences for saved enrollment

// todo enrollment process validation, handling exceptions
// todo code refactor
// todo tests