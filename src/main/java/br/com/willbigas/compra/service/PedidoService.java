package br.com.willbigas.compra.service;

import br.com.willbigas.compra.model.Pedido;
import br.com.willbigas.compra.repository.PedidoRepository;
import br.com.willbigas.compra.service.exception.EntidadeNaoEncontradaException;
import br.com.willbigas.compra.service.producer.ProducerCompraEfetuada;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PedidoService  {

	private final PedidoRepository pedidoRepository;
	private final ProducerCompraEfetuada producerCompraEfetuada;

	public Pedido salvar(Pedido pedido) throws JsonProcessingException {
		pedido = pedidoRepository.save(pedido);
		producerCompraEfetuada.enviarPedido(pedido);
		return pedido;
	}

	public Pedido buscarOuFalharPorId(Long id) {
		return pedidoRepository.findById(id)
				.orElseThrow(()-> new EntidadeNaoEncontradaException("O pedido de id: " + id + " nao existe na base de dados!"));
	}

	public void excluir(Long id) {
		Pedido pedido = buscarOuFalharPorId(id);
		pedidoRepository.deleteById(pedido.getId());
	}
}

