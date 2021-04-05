package br.com.zup.desafio.Proposta.bloqueios;

public class BloqueioRequest {

	private String sistemaResponsavel;

	public BloqueioRequest(String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}

	@Deprecated
	public BloqueioRequest() {
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

}
