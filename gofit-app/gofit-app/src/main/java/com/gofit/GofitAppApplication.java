package com.gofit;

import com.gofit.userDAO.UserDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GofitAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(GofitAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserDAO userDAO) {
		return runner -> {

		};
	}

}
