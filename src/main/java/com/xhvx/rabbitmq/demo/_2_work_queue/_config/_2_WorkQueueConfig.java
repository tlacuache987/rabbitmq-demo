package com.xhvx.rabbitmq.demo._2_work_queue._config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.xhvx.rabbitmq.demo._2_work_queue.receiver._2_Receiver;
import com.xhvx.rabbitmq.demo._2_work_queue.sender._2_Sender;

@Profile({ "2-work-queues", "work-queues" })
@Configuration
public class _2_WorkQueueConfig {

	@Bean
	public Queue hello() {
		return new Queue("hello");
	}

	@Profile("receiver")
	private static class ReceiverConfig {

		@Bean
		public _2_Receiver receiver1() {
			return new _2_Receiver(1);
		}

		@Bean
		public _2_Receiver receiver2() {
			return new _2_Receiver(2);
		}
	}

	@Profile("receiver-single")
	private static class Receiver1Config {

		@Bean
		public _2_Receiver receiver() {
			return new _2_Receiver(0);
		}
	}

	@Profile("sender")
	@Bean
	public _2_Sender sender() {
		return new _2_Sender();
	}
}