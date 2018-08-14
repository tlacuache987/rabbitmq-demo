package com.xhvx.rabbitmq.demo._3_publish_subscribe._config;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.xhvx.rabbitmq.demo._3_publish_subscribe.receiver._3_Receiver;
import com.xhvx.rabbitmq.demo._3_publish_subscribe.sender._3_Sender;

@Profile({ "3-publish-subscribe", "pub-sub", "publish-subscribe" })
@Configuration
public class _3_PublishSubscribeConfig {

	@Bean
	public FanoutExchange fanout() {
		FanoutExchange f = new FanoutExchange("pub-sub.fanout.exchange");
		System.out.println(f);
		return f;
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
		public Binding binding1(FanoutExchange fanout, Queue autoDeleteQueue1) {
			return BindingBuilder.bind(autoDeleteQueue1).to(fanout);
		}

		@Bean
		public Binding binding2(FanoutExchange fanout, Queue autoDeleteQueue2) {
			return BindingBuilder.bind(autoDeleteQueue2).to(fanout);
		}

		@Bean
		public _3_Receiver receiver() {
			return new _3_Receiver();
		}
	}

	@Profile("sender")
	@Bean
	public _3_Sender sender() {
		return new _3_Sender();
	}
}