package com.xhvx.rabbitmq.demo.app;

import java.util.Arrays;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

public class RabbitMqApplicationRunner implements CommandLineRunner {

	@Autowired
	private Environment environment;

	@Value("${app.client.duration:0}")
	private int duration;

	@Autowired
	private ConfigurableApplicationContext ctx;

	@Override
	public void run(String... arg0) throws Exception {
		if (isSenderProfileActive())
			System.out.println("Ready ... running for " + duration + "ms");
		else
			System.out.println("Ready ... running forever");

		if (isSenderProfileActive())
			Thread.sleep(duration);

		if (isSenderProfileActive())
			ctx.close();
		else {
			System.out.println("Press return to exit");
			new Scanner(System.in).nextLine();
			ctx.close();
		}
	}

	public boolean isSenderProfileActive() {
		if (Arrays.stream(environment.getActiveProfiles()).anyMatch(env -> (env.equalsIgnoreCase("sender")))) {
			return true;
		}
		return false;

	}
}
