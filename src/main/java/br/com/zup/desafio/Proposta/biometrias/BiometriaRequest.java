package br.com.zup.desafio.Proposta.biometrias;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.desafio.Proposta.cartoes.Cartao;

public class BiometriaRequest {

	@Size(max = 1024)
	@NotBlank
	private String digital;

	@Deprecated
	public BiometriaRequest() {
	}

	public BiometriaRequest(@Size(max = 1024) @NotBlank String digital) {
		this.digital = digital;
	}

	public String getDigital() {
		return digital;
	}

	public Biometria toModel(Long id, EntityManager em) {

		Cartao cartao = Optional.ofNullable(em.find(Cartao.class, id)).orElseThrow(() -> new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Ops! O Id deste Cartão não foi encontrado!"));
		
		return new Biometria(digital, cartao);
	
	}

}
