package br.com.zup.desafio.Proposta.cartoes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.zup.desafio.Proposta.Proposta;
import br.com.zup.desafio.Proposta.avisos.Aviso;
import br.com.zup.desafio.Proposta.bloqueios.BloqueioResponse;
import br.com.zup.desafio.Proposta.carteiras.CarteiraResponse;

public class CartaoResponseRouter {

	// NOSSO ID DO CARTAO CRIADO
	private String id;

	private String idProposta;

	private String titular;

	private LocalDateTime emitidoEm;

	private Double limite;

	private List<BloqueioResponse> bloqueios;

	private List<Aviso> avisos;

	private List<CarteiraResponse> carteiras;

	private List<Parcela> parcelas;

	private Renegociacao renegociacao;

	private VencimentoResponse vencimento;

	// FALTA CRIAR O MÉTODO toModel no VencimentoResponse
	public Cartao toModel(Proposta proposta) {
		return new Cartao(id, emitidoEm, proposta, limite,
				bloqueios.stream().map(BloqueioResponse::toModel).collect(Collectors.toList()), avisos,
				carteiras.stream().map(CarteiraResponse::toModel).collect(Collectors.toList()),
				parcelas,
				renegociacao, vencimento.toModel());
	}

	@Override
	public String toString() {
		return "CartaoResponse [id=" + id + ", idProposta=" + idProposta + ", titular=" + titular + ", emitidoEm="
				+ emitidoEm + ", limite=" + limite + ", bloqueios=" + bloqueios + ", avisos=" + avisos + ", carteiras="
				+ carteiras + ", parcelas=" + parcelas + ", renegociacao=" + renegociacao + ", vencimento=" + vencimento
				+ "]";
	}

	// INICIO GETTERS
	public String getId() {
		return id;
	}

	public String getIdProposta() {
		return idProposta;
	}

	public String getTitular() {
		return titular;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public Double getLimite() {
		return limite;
	}

	public List<BloqueioResponse> getBloqueios() {
		return bloqueios;
	}

	public List<Aviso> getAvisos() {
		return avisos;
	}

	public List<CarteiraResponse> getCarteiras() {
		return carteiras;
	}

	public List<Parcela> getParcelas() {
		return parcelas;
	}

	public Renegociacao getRenegociacao() {
		return renegociacao;
	}

	public VencimentoResponse getVencimento() {
		return vencimento;
	}

	public BloqueioResponse getUltimoBloqueio() {
		return bloqueios.get(bloqueios.size() - 1);
	}

	public CarteiraResponse getUltimaCarteira() {
		return carteiras.get(carteiras.size() - 1);
	}
	// FIM GETTERS

}
