package br.com.zup.desafio.Proposta.cartoes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "parcelas")
public class Parcela {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String idExterno;

	private int quantidade;

	private Double valor;

	// PODE HAVER MUITAS PARCELAS PARA APENAS 1 CARTÃO
	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public Parcela() {
	}

	public Parcela(@NotBlank String idExterno, int quantidade, Double valor, Cartao cartao) {
		this.idExterno = idExterno;
		this.quantidade = quantidade;
		this.valor = valor;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	public String getIdExterno() {
		return idExterno;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Double getValor() {
		return valor;
	}

	public Cartao getCartao() {
		return cartao;
	}

}
