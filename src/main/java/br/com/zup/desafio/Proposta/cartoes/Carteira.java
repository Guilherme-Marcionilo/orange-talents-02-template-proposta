package br.com.zup.desafio.Proposta.cartoes;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "carteiras")
public class Carteira {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String idExterno;

	private String email;

	private LocalDateTime associadaEm;

	private String emissor;

	// MUITAS CARTEIRAS PARA 1 CARTAO
	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public Carteira() {
	}

	public Carteira(@NotBlank String idExterno, String email, LocalDateTime associadaEm, String emissor,
			Cartao cartao) {
		this.idExterno = idExterno;
		this.email = email;
		this.associadaEm = associadaEm;
		this.emissor = emissor;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	public String getIdExterno() {
		return idExterno;
	}

	public String getEmail() {
		return email;
	}

	public LocalDateTime getAssociadaEm() {
		return associadaEm;
	}

	public String getEmissor() {
		return emissor;
	}

	public Cartao getCartao() {
		return cartao;
	}

}
