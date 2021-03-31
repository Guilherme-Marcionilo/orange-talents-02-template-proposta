package br.com.zup.desafio.Proposta.cartoes;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "vencimentos")
public class Vencimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String idExterno;

	private Integer dia;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
	private LocalDateTime dataCriacao;

	@Deprecated
	public Vencimento() {
	}

	public Vencimento(String idExterno, Integer dia, LocalDateTime dataCriacao) {
		this.idExterno = idExterno;
		this.dia = dia;
		this.dataCriacao = dataCriacao;
	}

	public Long getId() {
		return id;
	}

	public String getIdExterno() {
		return idExterno;
	}

	public Integer getDia() {
		return dia;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	@Override
	public String toString() {
		return "Vencimento [id=" + id + ", idExterno=" + idExterno + ", dia=" + dia + ", dataCriacao=" + dataCriacao
				+ "]";
	}
}
