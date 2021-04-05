package br.com.zup.desafio.Proposta.carteiras;

import java.time.LocalDateTime;

import br.com.zup.desafio.Proposta.cartoes.Cartao;

public class CarteiraResponse {

	private String id;

	private String email;

	private String associadaEm;

	private TipoCarteiraEnum emissor;

	private Cartao cartao;

	@Deprecated
	public CarteiraResponse() {
	}

	public Carteira toModel() {
		return new Carteira(id, email, LocalDateTime.parse(associadaEm), emissor, cartao);
	}

	public Carteira toModel(Cartao cartao) {
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

	public TipoCarteiraEnum getEmissor() {
		return emissor;
	}

	public Cartao getCartao() {
		return cartao;
	}

}
