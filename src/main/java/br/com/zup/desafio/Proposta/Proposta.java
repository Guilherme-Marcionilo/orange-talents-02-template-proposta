package br.com.zup.desafio.Proposta;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

import br.com.zup.desafio.Proposta.status.StatusProposta;
import br.com.zup.desafio.Proposta.status.StatusRequest;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 20)
	private String documento;

	@Email
	@Column(unique = true, length = 50)
	private String email;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String endereco;

	@Column(nullable = false)
	private BigDecimal salario;

	@Enumerated(EnumType.STRING)
	private PropostaStatus status;

//	@OneToOne
//	private Cartao cartao;

	@Deprecated
	public Proposta() {
	}

	public Proposta(String documento, @Email String email, String nome, String endereco, BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Long getId() {
		return id;
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

	public PropostaStatus getStatus() {
		return status;
	}

	public void setStatus(StatusProposta status) {
		if (status == StatusProposta.COM_RESTRICAO)
			this.status = PropostaStatus.NAO_ELEGIVEL;
		else if (status == StatusProposta.SEM_RESTRICAO)
			this.status = PropostaStatus.ELEGIVEL;
	}

	public StatusRequest toStatus() {
		return new StatusRequest(nome, documento, id);
	}

}
