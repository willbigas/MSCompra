package br.com.willbigas.compra.controller;

import br.com.willbigas.compra.CompraApplication;
import br.com.willbigas.compra.DadosMock;
import br.com.willbigas.compra.model.Pedido;
import br.com.willbigas.compra.service.PedidoService;
import br.com.willbigas.compra.service.exception.EntidadeNaoEncontradaException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CompraApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PedidoControllerTest {

	private final MockMvc mockMvc;
	private final PedidoService pedidoService;
	private final ObjectMapper objectMapper;

	private static final String ROTA_PEDIDO = "/pedido";

	private DadosMock dadosMock = new DadosMock();

	Long id = 1L;

	@Test
	@DisplayName("POST - Deve cadastrar um novo pedido com sucesso no banco de dados")
	@Order(1)
	void deveCadastrarPedidoComSucesso() throws Exception {
		Pedido pedido = dadosMock.getPedido();

		mockMvc.perform(post(ROTA_PEDIDO)
						.content(objectMapper.writeValueAsString(pedido))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());

		Pedido pedidoSalvo = pedidoService.buscarOuFalharPorId(id);

		assertNotNull(pedidoSalvo);
		assertEquals(pedidoSalvo.getId() , id);
	}

	@DisplayName("GET - Deve buscar o pedido com sucesso na base de dados")
	@Test
	@Order(2)
	void deveBuscarPedidoComSucesso() throws Exception {
		var id = 1L;

		mockMvc.perform(get(ROTA_PEDIDO.concat("/" + id)))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@DisplayName("GET - Deve falhar ao buscar pedido que nao existe")
	@Test
	@Order(3)
	void deveFalharAoBuscarPedidoQueNaoExiste() throws Exception {
		var id = 2L;

		mockMvc.perform(get(ROTA_PEDIDO.concat("/" + id)))
				.andDo(print())
				.andExpect(status().isNotFound());
	}

	@DisplayName("DELETE - Deve excluir um pedido com sucesso")
	@Test
	@Order(4)
	void deveExcluirUmPedidoComSucesso() throws Exception {
		var id = 1L;

		mockMvc.perform(delete(ROTA_PEDIDO.concat("/" + id)))
				.andDo(print())
				.andExpect(status().isNoContent());

		Throwable exception = assertThrows(EntidadeNaoEncontradaException.class, () -> pedidoService.excluir(id));

		assertEquals("O pedido de id: " + id + " nao existe na base de dados!", exception.getMessage());
	}

}