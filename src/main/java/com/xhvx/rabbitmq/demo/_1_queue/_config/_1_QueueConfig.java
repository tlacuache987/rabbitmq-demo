package com.xhvx.rabbitmq.demo._1_queue._config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.xhvx.rabbitmq.demo._1_queue.receiver._1_Receiver;
import com.xhvx.rabbitmq.demo._1_queue.sender._1_Sender;


@Profile({"1-queue","hello-world"})
@Configuration
public class _1_QueueConfig {

	@Bean
	public Queue hello() {
		return new Queue("hello");
	}

	@Profile("receiver")
	@Bean
	public _1_Receiver receiver() {
		return new _1_Receiver();
	}

	@Profile("sender")
	@Bean
	public _1_Sender sender() {
		return new _1_Sender();
	}
}