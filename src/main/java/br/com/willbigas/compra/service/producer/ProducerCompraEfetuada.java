package br.com.willbigas.compra.service.producer;

import br.com.willbigas.compra.model.Pedido;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProducerCompraEfetuada {

	private final RabbitTemplate rabbitTemplate;
	private final Queue queue;
	private final ObjectMapper mapper;

	public void enviarPedido(Pedido pedido) throws JsonProcessingException {
		log.info("Enviando mensagem {} para a fila {}", pedido.toString() , queue.getName());
		rabbitTemplate.convertAndSend(queue.getName() , mapper.writeValueAsString(pedido));
	}

}
