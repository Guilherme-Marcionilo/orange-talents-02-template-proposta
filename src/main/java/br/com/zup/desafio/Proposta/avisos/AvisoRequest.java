package br.com.zup.desafio.Proposta.avisos;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import br.com.zup.desafio.Proposta.cartoes.Cartao;

public class AvisoRequest {

	@NotBlank
	private String destino;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	private LocalDate validoAte;

	public Aviso toModel(Cartao cartao, String requestInfos, String agent) {

		return new Aviso(this, cartao, requestInfos, agent);

	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getValidoAte() {
		return validoAte;
	}

}