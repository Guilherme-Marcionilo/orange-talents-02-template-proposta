package br.com.zup.desafio.Proposta;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

import com.google.common.hash.Hashing;
import org.springframework.security.crypto.encrypt.Encryptors;
import java.nio.charset.StandardCharsets;

import br.com.zup.desafio.Proposta.cartoes.Cartao;
import br.com.zup.desafio.Proposta.cartoes.CartaoRequestRouter;
import br.com.zup.desafio.Proposta.status.StatusProposta;
import br.com.zup.desafio.Proposta.status.StatusRequest;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 20, nullable = false)
	private String documento;

	@Email
	@Column(unique = true, length = 50, nullable = false)
	private String email;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String endereco;

	@Column(nullable = false)
	private BigDecimal salario;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PropostaStatus status = PropostaStatus.NAO_ELEGIVEL;

	@OneToOne(cascade = CascadeType.MERGE)
	private Cartao cartao;

	@Column(length = 64, unique = true)
	private String documentoHashed;

	public Proposta(String documento, @Email String email, String nome, String endereco, BigDecimal salario) {
		this.documento = documento;
		this.documento = Encryptors.text("abcabc", "cbacba").encrypt(documento);
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
		this.documentoHashed = Hashing.sha256().hashString(documento, StandardCharsets.UTF_8).toString();
	}

	public void setStatus(StatusProposta status) {
		if (status == StatusProposta.COM_RESTRICAO)
			this.status = PropostaStatus.NAO_ELEGIVEL;
		else if (status == StatusProposta.SEM_RESTRICAO)
			this.status = PropostaStatus.ELEGIVEL;
	}

	public StatusRequest toStatus() {
		return new StatusRequest(new Proposta(this.documento, this.email, this.nome, this.endereco, this.salario));
	}

	@Override
	public String toString() {
		return "Proposta [id=" + id + ", documento=" + documento + ", email=" + email + ", nome=" + nome + ", endereco="
				+ endereco + ", salario=" + salario + ", status=" + status + "]";
	}

	public void updateStatus(PropostaStatus status) {

		if (status == null) {
			throw new IllegalArgumentException("Ops! O status não pode ser nulo!");
		}

		this.status = status;
	}

	public CartaoRequestRouter toCartaoRequest() {

		return new CartaoRequestRouter(documento, nome, id);

	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Proposta))
			return false;
		Proposta proposta = (Proposta) o;
		return Objects.equals(getId(), proposta.getId()) && Objects.equals(getDocumento(), proposta.getDocumento())
				&& Objects.equals(getNome(), proposta.getNome()) && Objects.equals(getEmail(), proposta.getEmail())
				&& Objects.equals(getEndereco(), proposta.getEndereco())
				&& Objects.equals(getSalario(), proposta.getSalario()) && getStatus() == proposta.getStatus();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getDocumento(), getNome(), getEmail(), getEndereco(), getSalario(), getStatus());
	}

	public void toCartaoResponse(Cartao cartao) {

		this.cartao = cartao;

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

	public Cartao getCartao() {
		return cartao;
	}

	@Deprecated
	public Proposta() {
	}
}
