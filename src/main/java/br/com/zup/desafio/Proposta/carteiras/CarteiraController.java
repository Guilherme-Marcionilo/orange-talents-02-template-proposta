package br.com.zup.desafio.Proposta.carteiras;

import java.net.URI;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.desafio.Proposta.cartoes.Cartao;
import br.com.zup.desafio.Proposta.cartoes.CartaoResponseRouter;
import br.com.zup.desafio.Proposta.cartoes.CartaoRouter;
import feign.FeignException;

@RestController
@RequestMapping("/carteiras")
public class CarteiraController {

	@PersistenceContext
	private EntityManager em;

	// ESTE É O NOSSO FEIGN (UM OUTRO CONTROLADOR QUE FAZ AS REQUISIÇÕES NO SISTEMA
	// EXTERNO)
	@Autowired
	private CartaoRouter cartaoRouter;

	@PostMapping
	@Transactional
	public ResponseEntity<?> criar(@PathVariable Long id, @RequestBody @Valid CarteiraRequest form,
			UriComponentsBuilder uriBuilder) {
		Cartao cartao = Optional.ofNullable(em.find(Cartao.class, id)).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID de cartão não foi encontrado!"));

		try {

			cartaoRouter.criaCarteira(cartao.getNumeroCartao(), form);
			CartaoResponseRouter response = cartaoRouter.buscaCartaoPorId(cartao.getNumeroCartao());

			Carteira carteira = response.getUltimaCarteira().toModel(cartao);

			cartao.addCarteira(carteira);

			em.merge(cartao);

			URI uri = uriBuilder.path("/carteiras/{id}").buildAndExpand(carteira.getId()).toUri();
			
			return ResponseEntity.created(uri).build();

		} catch (FeignException f) {

			return ResponseEntity.unprocessableEntity().build();
		}

	}

}
