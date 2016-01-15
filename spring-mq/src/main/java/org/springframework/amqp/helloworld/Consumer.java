package org.springframework.amqp.helloworld;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Consumer {
	
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
		AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);
		while (true) {
			System.out.println("Received: " + amqpTemplate.receiveAndConvert());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
