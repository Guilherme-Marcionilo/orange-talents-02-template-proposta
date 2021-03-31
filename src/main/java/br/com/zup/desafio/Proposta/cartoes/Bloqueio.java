package br.com.zup.desafio.Proposta.cartoes;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bloqueios")
public class Bloqueio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String idExterno;

	private LocalDateTime bloqueadoEm;

	private String sistemaResponsavel;

	private boolean ativo;

	// MUITOS BLOQUEIOS PARA 1 CARTÃO
	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public Bloqueio() {
	}

	public Bloqueio(String idExterno, LocalDateTime bloqueadoEm, String sistemaResponsavel, boolean ativo,
			Cartao cartao) {
		this.idExterno = idExterno;
		this.bloqueadoEm = bloqueadoEm;
		this.sistemaResponsavel = sistemaResponsavel;
		this.ativo = ativo;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	public String getIdExterno() {
		return idExterno;
	}

	public LocalDateTime getBloqueadoEm() {
		return bloqueadoEm;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public Cartao getCartao() {
		return cartao;
	}

}
