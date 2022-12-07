package br.com.willbigas.compra.service.producer;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueCompraEfetuadaConfig {

	@Value("${queue_name_producer}")
	private String queueName;

	@Bean
	public Queue queue() {
		return new Queue(queueName, true);
	}
}
