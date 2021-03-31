package br.com.zup.desafio.Proposta.status;

import br.com.zup.desafio.Proposta.PropostaStatus;

public class StatusResponse {

	private Long idProposta;

	private String documento;

	private String nome;

	private String status;

	public Long getIdProposta() {
		return idProposta;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}
	

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "StatusResponse [idProposta=" + idProposta + ", documento=" + documento + ", nome=" + nome
				+ ", resultadoSolicitacao=" + status + "]";
	}

	public PropostaStatus toModel() {
	
		if ("COM_RESTRICAO".equals(status)) {
			return PropostaStatus.NAO_ELEGIVEL;
		}
		else {
			return PropostaStatus.ELEGIVEL;
		}
	}	
	
}
