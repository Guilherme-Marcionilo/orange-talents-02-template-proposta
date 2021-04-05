package br.com.zup.desafio.Proposta.avisos;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.desafio.Proposta.cartoes.Cartao;
import br.com.zup.desafio.Proposta.cartoes.CartaoRouter;
import feign.FeignException;

@RestController
@RequestMapping("/avisos")
public class AvisoController {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private CartaoRouter cartaoRouter;

	@PostMapping("/{id}")
	public ResponseEntity<?> criar(@PathVariable Long id, @RequestBody @Valid AvisoRequest form,
			HttpServletRequest requestInfos, @RequestHeader("user-agent") String agent) {
		Cartao cartao = Optional.ofNullable(em.find(Cartao.class, id)).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID de cartão não foi encontrado!"));

		try {
			cartaoRouter.notificaAviso(cartao.getNumeroCartao(), form);

			Aviso aviso = form.toModel(cartao, requestInfos.getRemoteAddr(), agent);

			em.merge(aviso);

			return ResponseEntity.ok().build();
		} catch (FeignException e) {
			return ResponseEntity.badRequest().build();
		}

	}

}
