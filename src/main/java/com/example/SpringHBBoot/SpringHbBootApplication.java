package com.example.SpringHBBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
//@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
//@EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class })

@ComponentScan(basePackages = "com.example.*")
public class SpringHbBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringHbBootApplication.class, args);
		System.out.println("Hello Buddy");
	}

}
