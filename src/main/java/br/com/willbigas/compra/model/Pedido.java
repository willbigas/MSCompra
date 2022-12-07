package br.com.willbigas.compra.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Entity(name = "tb_pedido")
public class Pedido implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String nome;

	@Min(1)
	private Long produto;

	@NotNull
	@Min(1)
	private BigDecimal valor;

	@NotNull
	@Past
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date dataCompra;

	@NotBlank
	@CPF
	private String cpfCliente;

	@NotBlank
	private String cep;

	@NotBlank
	@Email
	private String email;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "cartao_id")
	private Cartao cartao;
}
