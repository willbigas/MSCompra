package br.com.willbigas.compra.controller;

import br.com.willbigas.compra.model.Pedido;
import br.com.willbigas.compra.service.PedidoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
}
