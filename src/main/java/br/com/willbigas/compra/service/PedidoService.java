package br.com.willbigas.compra.service;

import br.com.willbigas.compra.model.Pedido;
import br.com.willbigas.compra.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PedidoService {

	private final PedidoRepository pedidoRepository;

	public Pedido salvar(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
}

