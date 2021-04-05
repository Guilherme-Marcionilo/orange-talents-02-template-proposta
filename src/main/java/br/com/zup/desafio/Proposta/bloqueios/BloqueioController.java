package br.com.zup.desafio.Proposta.bloqueios;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.desafio.Proposta.cartoes.Cartao;
import br.com.zup.desafio.Proposta.cartoes.CartaoResponseRouter;
import br.com.zup.desafio.Proposta.cartoes.CartaoRouter;
import feign.FeignException;

@RestController
@RequestMapping("/bloqueios")
public class BloqueioController {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private CartaoRouter cartaoRouter;

	@PostMapping("/{id}")
	@Transactional
	// RequestHeader torna obrigatório a string agent, SOLICITANDO UM user-agent
	public ResponseEntity<?> bloqueiaCartao(@PathVariable Long id, HttpServletRequest requestInfos,
			@RequestHeader("user-agent") String agent) {

		//caso o nosso cartão venha nullo vai levar um not found!
		Cartao cartao = Optional.ofNullable(em.find(Cartao.class, id))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Id de cartão não encontrado"));
		
		try {
			cartaoRouter.bloqueio(cartao.getNumeroCartao(), new BloqueioRequest("Proposta"));
			
			CartaoResponseRouter respostaCartao = cartaoRouter.buscaCartaoPorId(cartao.getNumeroCartao());
			
			Bloqueio bloqueio = respostaCartao.getUltimoBloqueio().toModel();
			
			bloqueio.setInformacoesDeRequest(requestInfos.getRemoteAddr(), agent, cartao);
			
			cartao.addBloqueio(bloqueio);
			
			em.merge(cartao);
			
		}catch(FeignException f) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		return ResponseEntity.ok().build();
	}

}
