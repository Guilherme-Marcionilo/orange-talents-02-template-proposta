package br.com.zup.desafio.Proposta.status;

public class StatusResponse {

	private Long idProposta;

	private String documento;

	private String nome;

	private StatusProposta resultadoSolicitacao;

	public Long getIdProposta() {
		return idProposta;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public StatusProposta getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

}
