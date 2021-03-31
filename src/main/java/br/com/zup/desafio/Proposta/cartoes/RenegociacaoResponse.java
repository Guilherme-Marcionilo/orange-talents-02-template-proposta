package br.com.zup.desafio.Proposta.cartoes;

import java.time.LocalDateTime;

public class RenegociacaoResponse {

	//OBS: ESSE É O NOSSO ID_EXTERNO
	private String id;

	private Integer quantidade;

	private Double valor;

	private String dataDeCriacao;

	public Renegociacao toModel() {
		return new Renegociacao(id, quantidade, valor, LocalDateTime.parse(dataDeCriacao));
	}

	public String getId() {
		return id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Double getValor() {
		return valor;
	}

	public String getDataDeCriacao() {
		return dataDeCriacao;
	}
	
	@Deprecated
	public RenegociacaoResponse() {}
}
