package com.xhvx.rabbitmq.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.xhvx.rabbitmq.demo.app.RabbitMqApplicationRunner;

@EnableScheduling
@SpringBootApplication
public class Application {

	@Profile("usage_message")
	@Bean
	public CommandLineRunner usage() {
		return new CommandLineRunner() {

			@Override
			public void run(String... arg0) throws Exception {
				System.out.println("This app uses Spring Profiles to control its behavior.\n");
				System.out.println("Sample usage: java -jar rabbitmq-demo.jar --spring.profiles.active=hello-world,sender");
				System.out.println("or, mvn spring-boot:run -Dspring.profiles.active=hello-world,sender");
			}
		};
	}

	@Profile("!usage_message")
	@Bean
	public CommandLineRunner tutorial() {
		return new RabbitMqApplicationRunner();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
