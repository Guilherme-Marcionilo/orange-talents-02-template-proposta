package br.com.zup.desafio.Proposta.cartoes;

import java.time.LocalDateTime;

public class CarteiraResponse {

	private String id;

	private String email;

	private String associadaEm;

	private String emissor;

	private Cartao cartao;

	@Deprecated
	public CarteiraResponse() {
	}

	public Carteira toModel() {
		return new Carteira(id, email, LocalDateTime.parse(associadaEm), emissor, cartao);
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getAssociadaEm() {
		return associadaEm;
	}

	public String getEmissor() {
		return emissor;
	}

	public Cartao getCartao() {
		return cartao;
	}

}
