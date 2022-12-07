package br.com.willbigas.compra.service;

import br.com.willbigas.compra.DadosMock;
import br.com.willbigas.compra.model.Pedido;
import br.com.willbigas.compra.repository.PedidoRepository;
import br.com.willbigas.compra.service.exception.NegocioException;
import br.com.willbigas.compra.service.producer.ProducerCompraEfetuada;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {

	@InjectMocks
	private PedidoService pedidoService;

	@Mock
	private PedidoRepository pedidoRepository;

	@Mock
	private ProducerCompraEfetuada producer;

	private DadosMock mock = new DadosMock();

	@DisplayName("Salvar pedido com sucesso")
	@Test
	void deveSalvarUmPedidoComSucesso() throws JsonProcessingException {
		var pedidoMok = mock.getPedido();

		when(pedidoRepository.save(Mockito.any(Pedido.class))).thenReturn(pedidoMok);
		doNothing().when(producer).enviarPedido(Mockito.any(Pedido.class));

		var pedidoSalvo = pedidoService.salvar(pedidoMok);

		assertEquals(pedidoMok.getCep(), pedidoSalvo.getCep());
		assertNotNull(pedidoSalvo.getCep());
	}

	@DisplayName("Deve falhar na busca de pedido que nao existe")
	@Test
	void deveFalharNaBuscaDePedidoNaoExistente() {
		var id = 1L;

		Throwable exception = assertThrows(NegocioException.class, () -> {
			Pedido pedidoSalvo = pedidoService.buscarOuFalharPorId(id);
		});

		assertEquals("O pedido de id: " + id + " nao existe na base de dados!", exception.getMessage());
	}

	@DisplayName("Deve buscar um pedido com sucesso na base de dados")
	@Test
	void deveBuscarPedidoComSucesso() {
		var pedidoMok = mock.getPedidoSalvo();
		var id = 1L;

		when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedidoMok));

		var pedidoSalvo = pedidoService.buscarOuFalharPorId(id);

		assertEquals(pedidoMok.getId(), pedidoSalvo.getId());
		assertNotNull(pedidoSalvo);
		Mockito.verify(pedidoRepository, Mockito.atLeastOnce()).findById(id);
	}

	@DisplayName("Deve excluir o pedido com sucesso")
	@Test
	void deveExcluirPedidoComSucesso() {
		var pedidoMok = mock.getPedidoSalvo();
		var id = 1L;

		when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedidoMok));
		doNothing().when(pedidoRepository).deleteById(id);

		pedidoService.excluir(id);
		Mockito.verify(pedidoRepository, Mockito.atLeastOnce()).deleteById(id);
	}

	@DisplayName("Deve falhar ao excluir o pedido que nao existe")
	@Test
	void deveFalharAoEXcluirPedidoNaoExistente() {
		var id = 1L;

		when(pedidoRepository.findById(id)).thenReturn(Optional.empty());

		Throwable exception = assertThrows(NegocioException.class, () -> pedidoService.excluir(id));

		assertEquals("O pedido de id: " + id + " nao existe na base de dados!", exception.getMessage());
	}

}