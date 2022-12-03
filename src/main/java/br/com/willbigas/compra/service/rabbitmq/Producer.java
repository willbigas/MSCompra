package br.com.willbigas.compra.service.rabbitmq;

import br.com.willbigas.compra.model.Pedido;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Producer {

	private final RabbitTemplate rabbitTemplate;
	private final Queue queue;
	private final ObjectMapper mapper;

	public void enviarPedido(Pedido pedido) throws JsonProcessingException {
		rabbitTemplate.convertAndSend(queue.getName() , mapper.writeValueAsString(pedido));
	}

}
