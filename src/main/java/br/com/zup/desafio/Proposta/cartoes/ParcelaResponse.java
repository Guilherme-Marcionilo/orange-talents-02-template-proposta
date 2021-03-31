package br.com.zup.desafio.Proposta.cartoes;

public class ParcelaResponse {

	private String id;

	private int quantidade;

	private Double valor;

	private Cartao cartao;

	@Deprecated
	public ParcelaResponse() {
	}

	public Parcela toModel() {
		return new Parcela(id, quantidade, valor, cartao);
	}

	public String getId() {
		return id;
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
