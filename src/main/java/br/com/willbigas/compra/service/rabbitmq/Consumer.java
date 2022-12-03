package br.com.willbigas.compra.service.rabbitmq;

import br.com.willbigas.compra.model.Pedido;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@RabbitListener(queues = {"${queue_name}"})
	public void consumer(@Payload Pedido pedido) {
		System.out.println("Mensagem recebida"  + pedido);
	}
}
