package br.com.willbigas.compra.service;

import br.com.willbigas.compra.model.Cartao;
import br.com.willbigas.compra.model.Pedido;
import br.com.willbigas.compra.repository.CartaoRepository;
import br.com.willbigas.compra.repository.PedidoRepository;
import br.com.willbigas.compra.service.rabbitmq.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PedidoService {

	private final PedidoRepository pedidoRepository;
	private final CartaoRepository cartaoRepository;
	private final Producer producer;

	public Pedido salvar(Pedido pedido) throws JsonProcessingException {
		pedido = pedidoRepository.save(pedido);
		producer.enviarPedido(pedido);
		return pedido;
	}
}

