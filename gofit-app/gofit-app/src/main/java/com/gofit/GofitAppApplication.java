package com.gofit;

import com.gofit.dao.UserDAO;
import com.gofit.entity.Roles;
import com.gofit.entity.User;
import com.gofit.service.RolesService;
import com.gofit.service.UserService;
import com.mysql.cj.xdevapi.SessionFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GofitAppApplication {

	private final PasswordEncoder passwordEncoder;

	public GofitAppApplication(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(GofitAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserService userService, RolesService rolesService) {
		return runner -> {

		};
	}

	public SessionFactory sessionFactory(EntityManagerFactory emf) {
		if (emf.unwrap(SessionFactory.class) == null) {
			throw new NullPointerException("Factory is not a hibernate factory");
		}
		return emf.unwrap(SessionFactory.class);
	}

}
