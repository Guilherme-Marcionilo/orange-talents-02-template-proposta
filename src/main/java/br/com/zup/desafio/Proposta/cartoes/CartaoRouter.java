package br.com.zup.desafio.Proposta.cartoes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cartoes", url = "${cartao.api}/api/cartoes")
public interface CartaoRouter {

	@PostMapping
	public CartaoResponseRouter criaCartao(@RequestBody CartaoRequestRouter form);
	
	
}
