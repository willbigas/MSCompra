package br.com.willbigas.compra.service;

import br.com.willbigas.compra.model.Pedido;
import br.com.willbigas.compra.repository.PedidoRepository;
import br.com.willbigas.compra.service.rabbitmq.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PedidoService {

	private final PedidoRepository pedidoRepository;
	private final Producer producer;

	public Pedido salvar(Pedido pedido) {
		pedido = pedidoRepository.save(pedido);
		producer.enviarPedido(pedido);
		return pedido;
	}
}

