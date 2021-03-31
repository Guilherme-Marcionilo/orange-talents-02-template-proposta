package br.com.zup.desafio.Proposta.cartoes;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "renegociacoes")
public class Renegociacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String idExterno;

	private Integer quantidade;

	private Double valor;

	private LocalDateTime dataDeCriacao;

	@Deprecated
	public Renegociacao() {
	}

	public Renegociacao(String idExterno, Integer quantidade, Double valor, LocalDateTime dataDeCriacao) {
		this.idExterno = idExterno;
		this.quantidade = quantidade;
		this.valor = valor;
		this.dataDeCriacao = dataDeCriacao;
	}

	public Long getId() {
		return id;
	}

	public String getIdExterno() {
		return idExterno;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Double getValor() {
		return valor;
	}

	public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}

}
