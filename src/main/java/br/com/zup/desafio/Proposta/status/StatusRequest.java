package br.com.zup.desafio.Proposta.status;

public class StatusRequest {

	private String documento;
	
	private String nome;
	
	private Long idProposta;
	
	@Deprecated
	public StatusRequest() {}
	
    public StatusRequest(String documento, String nome, Long idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
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
