package br.com.willbigas.compra;

import br.com.willbigas.compra.model.Pedido;

import java.math.BigDecimal;
import java.util.Date;

public class DadosMock {

	public Pedido getPedido() {
		return Pedido.builder()
				.nome("Lucas Barros")
				.produto(1L)
				.valor(BigDecimal.TEN)
				.dataCompra(new Date())
				.cpfCliente("100.521.859-58")
				.cep("12345678")
				.email("lucas-barros28@hotmail.com")
				.build();
	}

	public Pedido getPedidoSalvo() {
		return Pedido.builder()
				.id(1L)
				.nome("Lucas Barros")
				.produto(1L)
				.valor(BigDecimal.TEN)
				.dataCompra(new Date())
				.cpfCliente("100.521.859-58")
				.cep("12345678")
				.email("lucas-barros28@hotmail.com")
				.build();
	}
}
