package com.msys.web.view;

import com.msys.repository.OrderRepository;
import com.msys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.msys.entity.Article;
import com.msys.entity.Order;
import com.msys.entity.OrderItem;
import com.msys.entity.Supplier;
import com.msys.entity.User;

//@EnableJpaRepositories("com.msys.repository")
@EnableJpaRepositories(basePackageClasses = OrderRepository.class)
@EnableAutoConfiguration
@EntityScan("com.msys.entity")
@ComponentScan("com.msys.web.login")
@SpringBootApplication

public class StoreMSysApplication /*implements CommandLineRunner*/ {

	private static final Logger log = LoggerFactory.getLogger(StoreMSysApplication.class);

	@Autowired
	private OrderRepository orderRepository;

	public static void main(String[] args) {
		SpringApplication.run(StoreMSysApplication.class, args);
	}

	/*@Override
	@Transactional
	public void run(String... strings) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.mm.yyyy");
		OrderItem orderItemA = new OrderItem(new Article(12), 13, new Supplier(14));
		OrderItem orderItemB = new OrderItem(new Article(34), 63, new Supplier(67));

		orderRepository.save(new HashSet<Order>() {
			private static final long serialVersionUID = 1L;
			{
				add(new Order(formatter.parse("16.12.2016"), new HashSet<OrderItem>() {
					private static final long serialVersionUID = 1L;
					{
						add(orderItemA);
						add(orderItemB);
					}
				}, formatter.parse("14.12.2016"), formatter.parse("23.12.2016")));

				add(new Order(formatter.parse("16.12.2016"), new HashSet<OrderItem>() {
					private static final long serialVersionUID = 1L;
					{
						add(orderItemA);
						add(orderItemB);
					}
				}, formatter.parse("16.12.2016"), formatter.parse("23.12.2016")));
			}
		});

		for (Order order : orderRepository.findAll()) {
			log.info(order.toString());
		}
	}*/

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

			SimpleDateFormat formatter = new SimpleDateFormat("dd.mm.yyyy");
			
			userRepo.save(new User ("test", "pass", "firstname", "lastname", 
					12222L,"test@email.test", "adresss", 12312312L, formatter.parse("16.12.2016"),
					(byte)0));
		};
	}
}
