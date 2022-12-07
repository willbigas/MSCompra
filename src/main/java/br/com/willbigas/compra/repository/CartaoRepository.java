package br.com.willbigas.compra.repository;

import br.com.willbigas.compra.model.Cartao;
import br.com.willbigas.compra.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

}
