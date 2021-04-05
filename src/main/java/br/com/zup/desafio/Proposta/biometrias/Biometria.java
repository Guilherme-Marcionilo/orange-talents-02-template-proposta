package br.com.zup.desafio.Proposta.biometrias;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.zup.desafio.Proposta.cartoes.Cartao;

@Entity
@Table(name = "biometrias")
public class Biometria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 1024)
	private String digital;

	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public Biometria() {
	}

	public Biometria(String digital, Cartao cartao) {
		this.digital = digital;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	public String getDigital() {
		return digital;
	}

	public Cartao getCartao() {
		return cartao;
	}

}
