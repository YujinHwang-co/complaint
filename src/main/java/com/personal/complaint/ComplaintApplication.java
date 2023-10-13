package com.personal.complaint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class ComplaintApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComplaintApplication.class, args);
	}

}
