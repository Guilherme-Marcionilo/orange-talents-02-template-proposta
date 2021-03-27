package br.com.zup.desafio.Proposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

import br.com.zup.desafio.Proposta.compartilhado.UniqueValue;
import br.com.zup.desafio.Proposta.validacao.CPFouCNPJ;

public class NovaPropostaRequest {
	
	@NotBlank
	@CPFouCNPJ
	@UniqueValue(domainClass = Proposta.class, fieldName = "documento", message = "Ops! CPF ou CNPJ inválido, o valor deve ser único!")
	private String documento;
	
	@NotBlank
	@Email
	@UniqueValue(domainClass = Proposta.class, fieldName = "email", message = "Ops! Email inválido, o valor deve ser único!")
	private String email;
	
	@NotBlank
	private String nome;
	
	@NotBlank (message = "Ops! Endereço inválido!")
	private String endereco;
	
	@Positive (message = "Ops! O salário deve ser um valor positivo!")
	@NotNull()
	private BigDecimal salario;
	
	@Deprecated
	public NovaPropostaRequest() {}

	public NovaPropostaRequest(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotBlank(message = "Ops! Endereço inválido!") String endereco,
			@Positive(message = "Ops! O salário deve ser um valor positivo!") BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}



	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public Proposta toModel() {
		return new Proposta(this.documento, this.email, this.endereco, this.nome, this.salario);
	}

}
