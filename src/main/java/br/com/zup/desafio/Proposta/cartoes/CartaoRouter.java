package br.com.zup.desafio.Proposta.cartoes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cartoes", url = "http://localhost:8888/api/cartoes")
public interface CartaoRouter {

	@PostMapping
	public CartaoResponse criaCartao(@RequestBody CartaoRequest form);
	
	
}
