package br.com.zup.desafio.Proposta.bloqueios;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.zup.desafio.Proposta.cartoes.Cartao;

@Entity
@Table(name = "bloqueios")
public class Bloqueio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String idExterno;

	private LocalDateTime bloqueadoEm;

	private String sistemaResponsavel;

	private boolean ativo;

	// MUITOS BLOQUEIOS PARA 1 CARTÃO
	@ManyToOne
	private Cartao cartao;

	private String ipCliente;

	private String userAgent;

	public void setInformacoesDeRequest(String remoteAddr, String agent, Cartao cartao) {

		this.ipCliente = remoteAddr;
		this.userAgent = agent;
		this.cartao = cartao;
	}

	public Bloqueio(String idExterno, LocalDateTime bloqueadoEm, String sistemaResponsavel, boolean ativo,
			Cartao cartao) {
		this.idExterno = idExterno;
		this.bloqueadoEm = bloqueadoEm;
		this.sistemaResponsavel = sistemaResponsavel;
		this.ativo = ativo;
		this.cartao = cartao;
	}

	@Override
	public String toString() {
		return "Bloqueio [id=" + id + ", idExterno=" + idExterno + ", bloqueadoEm=" + bloqueadoEm
				+ ", sistemaResponsavel=" + sistemaResponsavel + ", ativo=" + ativo + ", cartao=" + cartao
				+ ", ipCliente=" + ipCliente + ", userAgent=" + userAgent + "]";
	}

	public Long getId() {
		return id;
	}

	public String getIdExterno() {
		return idExterno;
	}

	public LocalDateTime getBloqueadoEm() {
		return bloqueadoEm;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public String getIpCliente() {
		return ipCliente;
	}

	public String getUserAgent() {
		return userAgent;
	}

	@Deprecated
	public Bloqueio() {
	}
}
