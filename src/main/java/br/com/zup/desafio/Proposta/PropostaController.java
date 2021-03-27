package br.com.zup.desafio.Proposta;

import java.util.Optional;

import javax.validation.Valid;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.desafio.Proposta.status.StatusGateway;
import br.com.zup.desafio.Proposta.status.StatusResponse;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

	@Autowired
	private StatusGateway statusGateway;
	
	@Autowired
	private PropostaRepository propostaRepository;

	private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

	@PostMapping
	ResponseEntity<?> cadastrar(@Valid @RequestBody NovaPropostaRequest request,
			UriComponentsBuilder uriComponentsBuilder) {

		logger.info("Início: criação proposta");

		if (propostaRepository.existsByDocumento(request.getDocumento())) {

			logger.warn("Proposta não foi criada");

			Assert.assertTrue(propostaRepository.existsByDocumento(request.getDocumento()));

			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Ops! Isto não pode ser processado! Pois, o documento já existe!");

		}
		
		Proposta proposta = request.toModel();

		proposta = propostaRepository.save(proposta);
		
		StatusResponse response = statusGateway.status(proposta.toStatus());
		proposta.setStatus(response.getResultadoSolicitacao());
		
		logger.info("Proposta Criada com Sucesso!", proposta.getDocumento());

		return ResponseEntity
				.created(uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri()).build();

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> consultar(@PathVariable("id") Long id) {
		logger.info("Consultando proposta");

		Optional<Proposta> proposta = propostaRepository.findById(id);

		if (proposta.isPresent()) {
			Assert.assertNotNull(proposta);
			logger.info("Proposta encontrada");
			return ResponseEntity.ok(proposta);
		}

		logger.warn("Proposta não encontrada");
		return ResponseEntity.notFound().build();
	}

}
