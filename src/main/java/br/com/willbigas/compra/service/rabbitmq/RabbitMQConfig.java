package br.com.willbigas.compra.service.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Value("${queue_name}")
	private String queueName;

	@Bean
	public Queue queue() {
		return new Queue(queueName, true);
	}
}
