package br.com.zup.desafio.Proposta.cartoes;

import java.time.LocalDateTime;

public class VencimentoResponse {

	private String id;

	private Integer dia;

	private String dataDeCriacao;

	@Deprecated
	public VencimentoResponse() {
	}

	public Vencimento toModel() {
		return new Vencimento(id, dia, LocalDateTime.parse(dataDeCriacao));
	}

	public String getId() {
		return id;
	}

	public Integer getDia() {
		return dia;
	}

	public String getDataDeCriacao() {
		return dataDeCriacao;
	}

}
