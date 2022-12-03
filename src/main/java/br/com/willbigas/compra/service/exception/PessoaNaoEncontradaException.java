package br.com.willbigas.compra.service.exception;

public class PessoaNaoEncontradaException extends EntidadeNaoEncontradaException {

	public PessoaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public PessoaNaoEncontradaException(Long id) {
		this(String.format("Não existe uma pessoa com o id: %s na base de dados!", id));
	}
}
