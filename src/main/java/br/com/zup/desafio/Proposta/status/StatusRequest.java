package br.com.zup.desafio.Proposta.status;

import br.com.zup.desafio.Proposta.Proposta;

public class StatusRequest {

	private String documento;
	
	private String nome;
	
	private Long idProposta;
	
    public StatusRequest(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId();
    }
    
    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
