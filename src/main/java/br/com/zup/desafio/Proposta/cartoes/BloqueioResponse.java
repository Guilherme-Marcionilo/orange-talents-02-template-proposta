package br.com.zup.desafio.Proposta.cartoes;

import java.time.LocalDateTime;

public class BloqueioResponse {

	private String id;
	
	private String bloqueadoEm;
	
	private String sistemaResponsavel;
	
	private boolean ativo;
	
	private Cartao cartao;
	
	@Deprecated
	public BloqueioResponse() {}
	
    public Bloqueio toModel() {
        return new Bloqueio(id, LocalDateTime.parse(bloqueadoEm), sistemaResponsavel, ativo, cartao);
    }
}
