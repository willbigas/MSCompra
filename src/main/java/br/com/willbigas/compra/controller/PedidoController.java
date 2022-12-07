package br.com.willbigas.compra.controller;

import br.com.willbigas.compra.model.Pedido;
import br.com.willbigas.compra.service.PedidoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PedidoController {

	private final PedidoService pedidoService;

	@PostMapping
	public ResponseEntity<Pedido> salvar(@RequestBody @Valid Pedido pedido) throws JsonProcessingException {
		return ResponseEntity.ok(pedidoService.salvar(pedido));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> getPedidoPorId(@PathVariable Long id) {
		return ResponseEntity.ok(pedidoService.buscarOuFalharPorId(id));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		pedidoService.excluir(id);
	}
}
