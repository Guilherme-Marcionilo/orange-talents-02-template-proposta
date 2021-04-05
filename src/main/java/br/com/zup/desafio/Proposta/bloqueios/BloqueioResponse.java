package br.com.zup.desafio.Proposta.bloqueios;

import java.time.LocalDateTime;

import br.com.zup.desafio.Proposta.cartoes.Cartao;

public class BloqueioResponse {

	private String id;

	private String bloqueadoEm;

	private String sistemaResponsavel;

	private boolean ativo;

	private Cartao cartao;

	@Deprecated
	public BloqueioResponse() {
	}

	public Bloqueio toModel() {
		return new Bloqueio(id, LocalDateTime.parse(bloqueadoEm), sistemaResponsavel, ativo, cartao);
	}

	public String getId() {
		return id;
	}

	public String getBloqueadoEm() {
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
