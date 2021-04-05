package br.com.zup.desafio.Proposta.carteiras;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

public class CarteiraRequest {

	@NotBlank
	@Email
	private String email;

	@NotNull
	private TipoCarteiraEnum carteira;

	@Deprecated
	public CarteiraRequest() {
	}

	public String getEmail() {
		return email;
	}

	public TipoCarteiraEnum getCarteira() {
		return carteira;
	}

}
