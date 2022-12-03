package br.com.willbigas.compra.service.rabbitmq;

import br.com.willbigas.compra.model.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Producer {

	private final RabbitTemplate rabbitTemplate;
	private final Queue queue;

	@PostMapping
	public void enviarPedido(Pedido pedido) {
		rabbitTemplate.convertAndSend(queue.getName() , pedido);
	}

}
