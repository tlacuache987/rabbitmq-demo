package com.xhvx.rabbitmq.demo._2_work_queue.receiver;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@RabbitListener(queues = "hello")
public class _2_Receiver {

	private final int instance;

	private int min = 0;
	private int max = 10;

	public _2_Receiver(int i) {
		this.instance = i;
	}

	@RabbitHandler
	public void receive(String in) throws InterruptedException {
		StopWatch watch = new StopWatch();
		watch.start();
		System.out.println("instance " + this.instance + " [x] Received '" + in + "'");
		doWork(in);

		if (ThreadLocalRandom.current().nextInt(min, max + 1) % 2 == 0) {
			String message = "instance " + this.instance + " [xxx] Cant process message '" + in + "'";
			System.out.println(message);
			throw new RuntimeException(message);
		}
		watch.stop();
		System.out.println("instance " + this.instance + " [x] Done in " + watch.getTotalTimeSeconds() + "s");
	}

	private void doWork(String in) throws InterruptedException {
		for (char ch : in.toCharArray()) {
			if (ch == '.') {
				Thread.sleep(1000);
			}
		}
	}
}