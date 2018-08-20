package com.xhvx.rabbitmq.demo._4_routing_queue._config;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.xhvx.rabbitmq.demo._4_routing_queue.receiver._4_Receiver;
import com.xhvx.rabbitmq.demo._4_routing_queue.sender._4_Sender;

@Profile({ "4-routing-key", "routing-key", "routing" })
@Configuration
public class _4_RoutingConfig {

	@Bean
	public DirectExchange direct() {
		DirectExchange d = new DirectExchange("routing.direct.exchange");
		System.out.println(d);
		return d;
	}

	@Profile("receiver")
	private static class ReceiverConfig {

		@Bean
		public Queue autoDeleteQueue1() {
			// AnonymousQueue or UniquelyNamedQueue
			return new AnonymousQueue();
		}

		@Bean
		public Queue autoDeleteQueue2() {
			// AnonymousQueue or UniquelyNamedQueue
			return new AnonymousQueue();
		}

		@Bean
		public Binding binding1a(DirectExchange direct, Queue autoDeleteQueue1) {
			return BindingBuilder.bind(autoDeleteQueue1).to(direct).with("orange");
		}
		
		@Bean
		public Binding binding1b(DirectExchange direct, Queue autoDeleteQueue1) {
			return BindingBuilder.bind(autoDeleteQueue1).to(direct).with("black");
		}

		@Bean
		public Binding binding2a(DirectExchange direct, Queue autoDeleteQueue2) {
			return BindingBuilder.bind(autoDeleteQueue2).to(direct).with("green");
		}
		
		@Bean
		public Binding binding2b(DirectExchange direct, Queue autoDeleteQueue2) {
			return BindingBuilder.bind(autoDeleteQueue2).to(direct).with("black");
		}

		@Bean
		public _4_Receiver receiver() {
			return new _4_Receiver();
		}
	}

	@Profile("sender")
	@Bean
	public _4_Sender sender() {
		return new _4_Sender();
	}
}