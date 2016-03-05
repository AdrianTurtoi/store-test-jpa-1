package com.msys.web.view;

import com.msys.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.msys.entity.User;

@SpringBootApplication
@EnableJpaRepositories("com.msys.repository")
@EntityScan("com.msys.entity")
@ComponentScan("com.msys.web.login")
public class StoreMSysApplication {

	private static final Logger log = LoggerFactory.getLogger(StoreMSysApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StoreMSysApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(UserRepository userRepo) {
		return (args) -> {

			log.info("User found with findAll():");
			log.info("-------------------------------");
			for (User userAll : userRepo.findAll()) {
				log.info(userAll.toString());
			}
			log.info("");

			// fetch an individual USer by ID
			User userOne = userRepo.findOne(7l);
			log.info("User found with findOne(7L):");
			log.info("--------------------------------");
			log.info(userOne.toString()); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			log.info("");

			// fetch Users by last name
			log.info("USer found with findByName('adrian.turtoi@gmail.com'):");
			log.info("--------------------------------------------");
			log.info(userRepo.findByEmail("adrian.turtoi@gmail.com").toString());
			log.info("");

		};
	}
}
