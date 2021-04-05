package br.com.zup.desafio.Proposta.biometrias;

public class BiometriaResponse {

	private Long id;

	private String digital;

	@Deprecated
	public BiometriaResponse() {
	}

	public BiometriaResponse(Biometria biometria) {
		this.id = biometria.getId();
		this.digital = biometria.getDigital();
	}

	public Long getId() {
		return id;
	}

	public String getDigital() {
		return digital;
	}

}
