package br.com.zup.desafio.Proposta.cartoes;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zup.desafio.Proposta.Proposta;

@Entity
@Table(name = "cartoes")
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String numeroCartao;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern = "yyy-MM-dd@HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
	private LocalDateTime emitidoEm;

	// UM CARTÃO PARA UMA PROPOSTA
	@OneToOne
	private Proposta proposta;

	private Double limite;

	// UM CARTÃO PODE TER MUITOS BLOQUEIOS
	@OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
	private List<Bloqueio> bloqueios;

	// UM CARTÃO PODE TER VÁRIOS AVISOS
	@OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
	private List<Aviso> avisos;

	// UM CARTÃO PODE TER MUITAS CARTEIRAS
	@OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
	private List<Carteira> carteiras;

	// UM CARTÃO PODE FAZER MUITAS PARCELAS
	@OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
	private List<Parcela> parcelas;

	// UM CARTÃO PODE FAZER APENAS UMA RENEGOCIACAO
	@OneToOne(cascade = CascadeType.MERGE)
	private Renegociacao renegociacao;

	@OneToOne(cascade = CascadeType.MERGE)
	private Vencimento vencimento;

	@Deprecated
	public Cartao() {
	}

	public Cartao(
					String numeroCartao,
					LocalDateTime emitidoEm,
					Proposta proposta,
					Double limite,
					List<Bloqueio> bloqueios,
					List<Aviso> avisos,
					List<Carteira> carteiras,
					List<Parcela> parcelas,
					Renegociacao renegociacao,
					Vencimento vencimento
				)
	{
		
		this.numeroCartao = numeroCartao;
		this.emitidoEm = emitidoEm;
		this.proposta = proposta;
		this.limite = limite;
		this.bloqueios = bloqueios;
		this.avisos = avisos;
		this.carteiras = carteiras;
		this.parcelas = parcelas;
		this.renegociacao = renegociacao;
		this.vencimento = vencimento;
	}

	public Long getId() {
		return id;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public Proposta getProposta() {
		return proposta;
	}

	public Double getLimite() {
		return limite;
	}

	public List<Bloqueio> getBloqueios() {
		return bloqueios;
	}

	public List<Aviso> getAvisos() {
		return avisos;
	}

	public List<Carteira> getCarteiras() {
		return carteiras;
	}

	public List<Parcela> getParcelas() {
		return parcelas;
	}

	public Renegociacao getRenegociacao() {
		return renegociacao;
	}

	public Vencimento getVencimento() {
		return vencimento;
	}

	@Override
	public String toString() {
		return "Cartao [id=" + id + ", numeroCartao=" + numeroCartao + ", emitidoEm=" + emitidoEm + ", proposta="
				+ proposta + ", limite=" + limite + ", bloqueios=" + bloqueios + ", avisos=" + avisos + ", carteiras="
				+ carteiras + ", parcelas=" + parcelas + ", renegociacao=" + renegociacao + ", vencimento=" + vencimento
				+ "]";
	}

}
